package com.ljjmk94.japanese_dictionary_n1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljjmk94.japanese_dictionary_n1.dao.WordDao;
import com.ljjmk94.japanese_dictionary_n1.dto.WordDto;
import com.ljjmk94.japanese_dictionary_n1.exception.WordNotFoundException;
import com.ljjmk94.japanese_dictionary_n1.model.Word;
import com.ljjmk94.japanese_dictionary_n1.request.WordRequest;

@Service
public class WordService implements IF_WordService {

    @Autowired
    private WordDao wordDao;

    // Basic CRUD 
    @Override
    public Integer createWord(WordRequest wordRequest) {
        if (
            wordRequest.getWord() == null ||
            wordRequest.getKana() == null ||
            wordRequest.getRomaji() == null ||
            wordRequest.getMeaning() == null ||
            wordRequest.getSentence() == null ||
            wordRequest.getSentenceMeaning() == null
        ) {
            throw new IllegalArgumentException("All Fields Must Be Provided!");
        }
        if (wordDao.wordExistsByKeywords(
            wordRequest.getWord(), wordRequest.getKana(), wordRequest.getRomaji())
        ) {
            throw new IllegalStateException("The Word Already Exists!");
        }
        return wordDao.createWord(wordRequest);
    }

    @Override
    public Word getWordById(Integer wordId) {
        Word word = wordDao.getWordById(wordId);
        if (word == null) {
            throw new WordNotFoundException(wordId);
        }
        return word;
    }

    @Override
    public void updateWord(Integer wordId, WordRequest wordRequest) {
        if (
            wordRequest.getWord() == null ||
            wordRequest.getKana() == null ||
            wordRequest.getRomaji() == null ||
            wordRequest.getMeaning() == null ||
            wordRequest.getSentence() == null ||
            wordRequest.getSentenceMeaning() == null
        ) {
            throw new IllegalArgumentException("All Fields Must Be Provided!");
        }
        wordDao.updateWord(wordId, wordRequest);
    }

    @Override
    public void deleteWord(Integer wordId) {
        wordDao.deleteWord(wordId);
    }

    // Others CRUD
    @Override
    public List<Word> getAllWords() {
        return wordDao.getAllWords();
    }

    @Override
    public List<Word> getAllWordsByKeywords(String word, String kana, String romaji) {
        boolean hasWord = word != null && !word.isBlank();
        boolean hasKana = kana != null && !kana.isBlank();
        boolean hasRomaji = romaji != null && !romaji.isBlank();
        if (hasWord && hasKana && hasRomaji) {
            return wordDao.getAllWordsByKeywords(word, kana, romaji);
        }
        if (hasWord) {
            return wordDao.getAllWordsByWord(word);
        }
        if (hasKana) {
            return wordDao.getAllWordsByKana(kana);
        }
        if (hasRomaji) {
            return wordDao.getAllWordsByRomaji(romaji);
        }
        return wordDao.getAllWordsByKeywords(word, kana, romaji);
    }

    @Override
    public Word getWordByKeywords(String word, String kana, String romaji) {
        boolean hasWord = word != null && !word.isBlank();
        boolean hasKana = kana != null && !kana.isBlank();
        boolean hasRomaji = romaji != null && !romaji.isBlank();
        List<Word> wordList;
        if (hasWord && hasKana && hasRomaji) {
            wordList = wordDao.getAllWordsByKeywords(word, kana, romaji);
        } else if (hasWord) {
            wordList = wordDao.getAllWordsByWord(word);
        } else if (hasKana) {
            wordList = wordDao.getAllWordsByKana(kana);
        } else if (hasRomaji) {
            wordList = wordDao.getAllWordsByRomaji(romaji);
        } else {
            wordList = wordDao.getAllWordsByKeywords(word, kana, romaji);
        }
        System.out.println(wordList.get(0) + "\n");
        return !wordList.isEmpty()? wordList.get(0):null;
    }

    @Override
    public WordDto convertToWordDto(Word word) {
        WordDto wordDto = new WordDto();
        wordDto.setId(word.getId());
        wordDto.setWord(word.getWord());
        wordDto.setKana(word.getKana());
        wordDto.setRomaji(word.getRomaji());
        wordDto.setMeaning(word.getMeaning());
        wordDto.setSentence(word.getSentence());
        wordDto.setSentenceMeaning(word.getSentenceMeaning());
        wordDto.setCreatedDate(word.getCreatedDate());
        wordDto.setLastModifiedDate(word.getLastModifiedDate());
        return wordDto;
    }

    @Override
    public List<WordDto> convertToWordDtoList(List<Word> words) {
        return words.stream().map(this::convertToWordDto).toList();
    }
    
}
