package com.ljjmk94.japanese_dictionary_n1.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ljjmk94.japanese_dictionary_n1.model.Word;
import com.ljjmk94.japanese_dictionary_n1.request.WordRequest;
import com.ljjmk94.japanese_dictionary_n1.rowMapper.WordRowMapper;

@Repository
public class WordDao implements IF_WordDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    // Basic CRUD 
    // CREATE 
    @Override
    public Integer createWord(WordRequest wordRequest) {
        // word 
        String wordSQL = """
            INSERT INTO n1_word (word, meaning, created_date, last_modified_date) 
            VALUES (:word, :meaning, :createdDate, :lastModifiedDate);
        """;
        Map<String, Object> wordMap = new HashMap<>();
        wordMap.put("word", wordRequest.getWord());
        wordMap.put("meaning", wordRequest.getMeaning());
        Date now = new Date();
        wordMap.put("createdDate", now);
        wordMap.put("lastModifiedDate", now);
        KeyHolder wordKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(wordSQL, new MapSqlParameterSource(wordMap), wordKeyHolder);
        
        // 獲取自動生成的 ID
        int wordId = wordKeyHolder.getKey().intValue();

        // kana 
        String kana_romajiSQL = """
            INSERT INTO n1_kana_romaji (word_id, kana, romaji) 
            VALUES (:word_id, :kana, :romaji);
        """;
        Map<String, Object> kana_romajiMap = new HashMap<>();
        kana_romajiMap.put("word_id", wordId);
        kana_romajiMap.put("kana", wordRequest.getKana());
        kana_romajiMap.put("romaji", wordRequest.getRomaji());
        namedParameterJdbcTemplate.update(kana_romajiSQL, kana_romajiMap);

        // sentence 
        String sentenceSQL = """
            INSERT INTO n1_sentence (word_id, sentence, sentence_meaning) 
            VALUES (:word_id, :sentence, :sentenceMeaning);  
        """;
        Map<String, Object> sentenceMap = new HashMap<>();
        sentenceMap.put("word_id", wordId);
        sentenceMap.put("sentence", wordRequest.getSentence());
        sentenceMap.put("sentenceMeaning", wordRequest.getSentenceMeaning());
        namedParameterJdbcTemplate.update(sentenceSQL, sentenceMap);

        return wordId;
    }
    // READ 
    @Override
    public Word getWordById(Integer wordId) {
        String sql = """
            SELECT n1_w.id, n1_w.word, n1_kr.kana, n1_kr.romaji, n1_w.meaning, 
                n1_s.sentence, n1_s.sentence_meaning, 
                n1_w.created_date, n1_w.last_modified_date
            FROM n1_word n1_w
            JOIN n1_kana_romaji n1_kr ON n1_w.id = n1_kr.word_id
            LEFT JOIN n1_sentence n1_s ON n1_w.id = n1_s.word_id
            WHERE n1_w.id = :wordId;
        """;
        Map<String, Object> map = new HashMap<>();
        map.put("wordId", wordId);
        List<Word> words = namedParameterJdbcTemplate.query(sql, map, new WordRowMapper());
        return words.isEmpty()? null:words.get(0);
    }
    // UPDATE 
    @Override
    public void updateWord(Integer wordId, WordRequest wordRequest) {
        // word 
        String wordSQL = """
            UPDATE n1_word SET 
                word = :word, 
                meaning = :meaning, 
                last_modified_date = :lastModifiedDate
            WHERE id = :wordId;
        """;
        Map<String, Object> wordMap = new HashMap<>();
        wordMap.put("wordId", wordId);
        wordMap.put("word", wordRequest.getWord());
        wordMap.put("meaning", wordRequest.getMeaning());
        Date now = new Date();
        wordMap.put("lastModifiedDate", now);
        namedParameterJdbcTemplate.update(wordSQL, wordMap);

        // kana 
        String kana_romajiSQL = """
            UPDATE n1_kana_romaji SET 
                kana = :kana, 
                romaji = :romaji
            WHERE word_id = :wordId;
        """;
        Map<String, Object> kana_romajiMap = new HashMap<>();
        kana_romajiMap.put("wordId", wordId);
        kana_romajiMap.put("kana", wordRequest.getKana());
        kana_romajiMap.put("romaji", wordRequest.getRomaji());
        namedParameterJdbcTemplate.update(kana_romajiSQL, kana_romajiMap);

        // sentence 
        String sentenceSQL = """
            UPDATE n1_sentence SET 
                sentence = :sentence, 
                sentence_meaning = :sentenceMeaning
            WHERE word_id = :wordId;
        """;
        Map<String, Object> sentenceMap = new HashMap<>();
        sentenceMap.put("wordId", wordId);
        sentenceMap.put("sentence", wordRequest.getSentence());
        sentenceMap.put("sentenceMeaning", wordRequest.getSentenceMeaning());
        namedParameterJdbcTemplate.update(sentenceSQL, sentenceMap);
    }
    // DELETE 
    @Override
    public void deleteWord(Integer wordId) {
        // kana 
        String kana_romajiSQL = """
            DELETE FROM n1_kana_romaji 
            WHERE word_id = :wordId;
        """;
        Map<String, Object> kana_romajiMap = new HashMap<>();
        kana_romajiMap.put("wordId", wordId);

        namedParameterJdbcTemplate.update(kana_romajiSQL, kana_romajiMap);

        // sentence 
        String sentenceSQL = """
            DELETE FROM n1_sentence 
            WHERE word_id = :wordId;
        """;
        Map<String, Object> sentenceMap = new HashMap<>();
        sentenceMap.put("wordId", wordId);

        namedParameterJdbcTemplate.update(sentenceSQL, sentenceMap);

        // word 
        String wordSQL = """
            DELETE FROM n1_word 
            WHERE id = :wordId;
        """;
        Map<String, Object> wordMap = new HashMap<>();
        wordMap.put("wordId", wordId);

        namedParameterJdbcTemplate.update(wordSQL, wordMap);
    }

    // Others CRUD
    @Override
    public List<Word> getAllWords() {
        String sql = """
            SELECT n1_w.id, n1_w.word, n1_kr.kana, n1_kr.romaji, n1_w.meaning, 
                n1_s.sentence, n1_s.sentence_meaning, 
                n1_w.created_date, n1_w.last_modified_date
            FROM n1_word n1_w
            JOIN n1_kana_romaji n1_kr ON n1_w.id = n1_kr.word_id
            LEFT JOIN n1_sentence n1_s ON n1_w.id = n1_s.word_id;
        """;
        return namedParameterJdbcTemplate.query(sql, new WordRowMapper());
    }

    @Override
    public List<Word> getAllWordsByKeywords(String word, String kana, String romaji) {
        String sql = """
            SELECT n1_w.id, n1_w.word, n1_kr.kana, n1_kr.romaji, n1_w.meaning, 
                n1_s.sentence, n1_s.sentence_meaning, 
                n1_w.created_date, n1_w.last_modified_date
            FROM n1_word n1_w
            JOIN n1_kana_romaji n1_kr ON n1_w.id = n1_kr.word_id
            LEFT JOIN n1_sentence n1_s ON n1_w.id = n1_s.word_id
            WHERE 1 = 1
        """;
        Map<String, Object> map = new HashMap<>();
        // 使用 LIKE 進行模糊查詢
        if (word != null) {
            // word 
            sql += "\nAND ((n1_w.word LIKE :word0 OR n1_w.word LIKE :word1)";
            map.put("word0", word + "%");
            map.put("word1", "%、" + word + "%");
            // kana 
            sql += "\nOR (n1_kr.kana LIKE :kana0 OR n1_kr.kana LIKE :kana1))";
            map.put("kana0", word + "%");
            map.put("kana1", "%、" + word + "%");
        }
        if (kana != null) {
            sql += "\nAND (n1_kr.kana LIKE :kana0 OR n1_kr.kana LIKE :kana1)";
            map.put("kana0", kana + "%");
            map.put("kana1", "%、" + kana + "%");
        }
        if (romaji != null) {
            sql += "\nAND (n1_kr.romaji LIKE :romaji0 OR n1_kr.romaji LIKE :romaji1)";
            map.put("romaji0", romaji + "%");
            map.put("romaji1", "%, " + romaji + "%");
        }
        sql += "ORDER BY n1_kr.kana COLLATE utf8mb4_ja_0900_as_cs ASC;";
        return namedParameterJdbcTemplate.query(sql, map, new WordRowMapper());
    }

    @Override
    public List<Word> getAllWordsByWord(String word) {
        String sql = """
            SELECT n1_w.id, n1_w.word, n1_kr.kana, n1_kr.romaji, n1_w.meaning, 
                n1_s.sentence, n1_s.sentence_meaning, 
                n1_w.created_date, n1_w.last_modified_date
            FROM n1_word n1_w
            JOIN n1_kana_romaji n1_kr ON n1_w.id = n1_kr.word_id
            LEFT JOIN n1_sentence n1_s ON n1_w.id = n1_s.word_id
            WHERE (n1_w.word LIKE :word0 OR n1_w.word LIKE :word1) 
                OR (n1_kr.kana LIKE :kana0 OR n1_kr.kana LIKE :kana1)
            ORDER BY n1_kr.kana COLLATE utf8mb4_ja_0900_as_cs ASC;
        """;
        Map<String, Object> map = new HashMap<>();
        map.put("word0", word + "%");
        map.put("word1", "%、" + word + "%");
        map.put("kana0", word + "%");
        map.put("kana1", "%、" + word + "%");
        return namedParameterJdbcTemplate.query(sql, map, new WordRowMapper());
    }

    @Override
    public List<Word> getAllWordsByKana(String kana) {
        String sql = """
            SELECT n1_w.id, n1_w.word, n1_kr.kana, n1_kr.romaji, n1_w.meaning, 
                n1_s.sentence, n1_s.sentence_meaning, 
                n1_w.created_date, n1_w.last_modified_date
            FROM n1_word n1_w
            JOIN n1_kana_romaji n1_kr ON n1_w.id = n1_kr.word_id
            LEFT JOIN n1_sentence n1_s ON n1_w.id = n1_s.word_id
            WHERE n1_kr.kana LIKE :kana0 OR n1_kr.kana LIKE :kana1
            ORDER BY n1_kr.kana COLLATE utf8mb4_ja_0900_as_cs ASC;
        """;
        Map<String, Object> map = new HashMap<>();
        map.put("kana0", kana + "%");
        map.put("kana1", "%、" + kana + "%");
        return namedParameterJdbcTemplate.query(sql, map, new WordRowMapper());
    }

    @Override
    public List<Word> getAllWordsByRomaji(String romaji) {
        String sql = """
            SELECT n1_w.id, n1_w.word, n1_kr.kana, n1_kr.romaji, n1_w.meaning, 
                n1_s.sentence, n1_s.sentence_meaning, 
                n1_w.created_date, n1_w.last_modified_date
            FROM n1_word n1_w
            JOIN n1_kana_romaji n1_kr ON n1_w.id = n1_kr.word_id
            LEFT JOIN n1_sentence n1_s ON n1_w.id = n1_s.word_id
            WHERE n1_kr.romaji LIKE :romaji0 OR n1_kr.romaji LIKE :romaji1
            ORDER BY n1_kr.kana COLLATE utf8mb4_ja_0900_as_cs ASC;
        """;
        Map<String, Object> map = new HashMap<>();
        map.put("romaji0", romaji + "%");
        map.put("romaji1", "%, " + romaji + "%");
        return namedParameterJdbcTemplate.query(sql, map, new WordRowMapper());
    }

    @Override
    public boolean wordExistsByKeywords(String word, String kana, String romaji) {
        String sql = """
            SELECT COUNT(*)
            FROM n1_word n1_w
            JOIN n1_kana_romaji n1_kr ON n1_w.id = n1_kr.word_id
            WHERE n1_w.word = :word AND n1_kr.kana = :kana AND n1_kr.romaji = :romaji;
        """;
        Map<String, Object> map = new HashMap<>();
        map.put("word", word);
        map.put("kana", kana);
        map.put("romaji", romaji);
        Integer count = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);
        return count != null && count > 0;
    }
    
}
