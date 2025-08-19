package com.ljjmk94.japanese_dictionary_n1.controller_service_dao.service;

import java.util.List;

import com.ljjmk94.japanese_dictionary_n1.format.Word;
import com.ljjmk94.japanese_dictionary_n1.format.WordRequest;

public interface ServiceInterface {

    public Integer saveData(WordRequest wordRequest);

    public List<Word> getAllData();

    public Word getDataById(Integer wordId);

    public List<Word> searchAllDataByKeywords(String word, String kana, String romaji);

    public Word searchDataByKeywords(String word, String kana, String romaji);

    public void updateData(Integer wordId, WordRequest wordRequest);

    public void deleteDataById(Integer wordId);

}
