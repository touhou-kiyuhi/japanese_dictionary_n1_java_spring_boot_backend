package com.ljjmk94.japanese_dictionary_n1.service;

import java.util.List;

import com.ljjmk94.japanese_dictionary_n1.dto.WordDto;
import com.ljjmk94.japanese_dictionary_n1.model.Word;
import com.ljjmk94.japanese_dictionary_n1.request.WordRequest;

public interface IF_WordService {
    
    // Basic CRUD 
    public Integer createWord(WordRequest wordRequest);

    public Word getWordById(Integer wordId);

    public void updateWord(Integer wordId, WordRequest wordRequest);

    public void deleteWord(Integer wordId);

    // Others CRUD
    public List<Word> getAllWords();

    public List<Word> getAllWordsByKeywords(String word, String kana, String romaji);

    public Word getWordByKeywords(String word, String kana, String romaji);

    public WordDto convertToWordDto(Word word);

    public List<WordDto> convertToWordDtoList(List<Word> words);

}
