package com.ljjmk94.japanese_dictionary_n1.controller_service_dao.dao;

import java.util.List;

import com.ljjmk94.japanese_dictionary_n1.format.Word;
import com.ljjmk94.japanese_dictionary_n1.format.WordRequest;
import com.ljjmk94.japanese_dictionary_n1.type.KeywordType;

public interface DaoInterface {
    
    public Integer saveData(WordRequest wordRequest);

    public List<Word> getAllDataByKeyword(KeywordType keywordType, Object keyword);

    public List<Word> searchAllDataByKeywords(String word, String kana, String romaji);

    public void updateDataById(Integer wordId, WordRequest wordRequest);

    public void deleteDataById(Integer wordId);

}
