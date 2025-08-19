package com.ljjmk94.japanese_dictionary_n1.controller_service_dao.dao;

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

import com.ljjmk94.japanese_dictionary_n1.controller_service_dao.dao.rowMapper.WordRowMapper;
import com.ljjmk94.japanese_dictionary_n1.format.Word;
import com.ljjmk94.japanese_dictionary_n1.format.WordRequest;
import com.ljjmk94.japanese_dictionary_n1.type.KeywordType;

@Repository
public class WordDao implements DaoInterface {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer saveData(WordRequest wordRequest) {
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
        String kana_romajiSQL = "INSERT INTO n1_kana_romaji (word_id, kana, romaji) VALUES (:word_id, :kana, :romaji);";
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

    @Override
    public List<Word> getAllDataByKeyword(KeywordType keywordType, Object keyword) {
        String sql = """
            SELECT n1_w.id, n1_w.word, n1_kr.kana, n1_kr.romaji, n1_w.meaning, n1_s.sentence, n1_s.sentence_meaning, n1_w.created_date, n1_w.last_modified_date
            FROM n1_word n1_w
            JOIN n1_kana_romaji n1_kr ON n1_w.id = n1_kr.word_id
            LEFT JOIN n1_sentence n1_s ON n1_w.id = n1_s.word_id
        """;
        Map<String, Object> map = new HashMap<>();
        switch (keywordType) {
            case ALL:
                break;
            case ID:
                sql += "\nWHERE n1_w.id = :wordId";
                map.put("wordId", keyword);
                break;
            default:
                break;
        }
        sql += ";";
        return namedParameterJdbcTemplate.query(sql, map, new WordRowMapper());
    }

    @Override
    public List<Word> searchAllDataByKeywords(String word, String kana, String romaji) {
        String sql = """
            SELECT n1_w.id, n1_w.word, n1_kr.kana, n1_kr.romaji, n1_w.meaning, n1_s.sentence, n1_s.sentence_meaning, n1_w.created_date, n1_w.last_modified_date
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
        sql += ";";
        return namedParameterJdbcTemplate.query(sql, map, new WordRowMapper());
    }

    @Override
    public void updateDataById(Integer wordId, WordRequest wordRequest) {
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

    @Override
    public void deleteDataById(Integer wordId) {
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
    
}
