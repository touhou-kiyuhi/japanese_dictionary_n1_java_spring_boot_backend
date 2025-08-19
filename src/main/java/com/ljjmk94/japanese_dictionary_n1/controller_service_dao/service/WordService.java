package com.ljjmk94.japanese_dictionary_n1.controller_service_dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljjmk94.japanese_dictionary_n1.controller_service_dao.dao.WordDao;
import com.ljjmk94.japanese_dictionary_n1.format.Word;
import com.ljjmk94.japanese_dictionary_n1.format.WordRequest;
import com.ljjmk94.japanese_dictionary_n1.type.KeywordType;

@Service
public class WordService implements ServiceInterface {

    @Autowired
    private WordDao wordDao;

    @Override
    public Integer saveData(WordRequest wordRequest) {
        return wordDao.saveData(wordRequest);
    }

    @Override
    public List<Word> getAllData() {
        return wordDao.getAllDataByKeyword(KeywordType.ALL, null);
    }

    @Override
    public Word getDataById(Integer wordId) {
        List<Word> wordList = wordDao.getAllDataByKeyword(KeywordType.ID, wordId);
        return wordList.size() > 0? wordList.get(0):null;
    }

    @Override
    public List<Word> searchAllDataByKeywords(String word, String kana, String romaji) {
        return wordDao.searchAllDataByKeywords(word, kana, romaji);
    }

    @Override
    public Word searchDataByKeywords(String word, String kana, String romaji) {
        List<Word> wordList = wordDao.searchAllDataByKeywords(word, kana, romaji);
        return wordList.size() > 0? wordList.get(0):null;
    }

    @Override
    public void updateData(Integer wordId, WordRequest wordRequest) {
        wordDao.updateDataById(wordId, wordRequest);
    }

    @Override
    public void deleteDataById(Integer wordId) {
        wordDao.deleteDataById(wordId);
    }
    
}
