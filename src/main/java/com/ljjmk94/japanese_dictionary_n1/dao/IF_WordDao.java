package com.ljjmk94.japanese_dictionary_n1.dao;

import java.util.List;

import com.ljjmk94.japanese_dictionary_n1.model.Word;
import com.ljjmk94.japanese_dictionary_n1.request.WordRequest;

public interface IF_WordDao {
    
    // Basic CRUD 
    // CREATE 
    public Integer createWord(WordRequest wordRequest);
    // READ 
    public Word getWordById(Integer wordId);
    // UPDATE 
    public void updateWord(Integer wordId, WordRequest wordRequest);
    // DELETE 
    public void deleteWord(Integer wordId);
    
    // Others CRUD
    public List<Word> getAllWords();

    public List<Word> getAllWordsByKeywords(String word, String kana, String romaji);

    public List<Word> getAllWordsByWord(String word);

    public List<Word> getAllWordsByKana(String kana);

    public List<Word> getAllWordsByRomaji(String romaji);

    // Others
    public boolean wordExistsByKeywords(String word, String kana, String romaji);

}
