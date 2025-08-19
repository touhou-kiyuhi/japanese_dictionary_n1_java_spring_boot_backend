package com.ljjmk94.japanese_dictionary_n1.controller_service_dao.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ljjmk94.japanese_dictionary_n1.controller_service_dao.service.WordService;
import com.ljjmk94.japanese_dictionary_n1.format.Word;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class OthersCRUDController {

    @Autowired
    private WordService wordService;

    // READ 
    // All Data 
    @GetMapping("data")
    public ResponseEntity<List<Word>> getAllData() {
        List<Word> wordList = wordService.getAllData();
        return ResponseEntity.status(HttpStatus.OK).body(wordList);
    }
    // Data By Word, Kana, Romaji
    // 參數
    @GetMapping("/data/search")
    public ResponseEntity<List<Word>> searchAllDataByKeywords(
        @RequestParam(required = false) String word, 
        @RequestParam(required = false) String kana, 
        @RequestParam(required = false) String romaji
        ) {
        List<Word> wordList = wordService.searchAllDataByKeywords(word, kana, romaji);
        return ResponseEntity.status(HttpStatus.OK).body(wordList);
    }
    @GetMapping("/data/update/search")
    public ResponseEntity<Word> updateSearchDataByKeywords(
        @RequestParam(required = false) String word, 
        @RequestParam(required = false) String kana, 
        @RequestParam(required = false) String romaji
        ) {
        Word wordData = wordService.searchDataByKeywords(word, kana, romaji);
        if (wordData != null) {
            return ResponseEntity.status(HttpStatus.OK).body(wordData);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/data/delete/search")
    public ResponseEntity<Word> deleteSearchDataByKeywords(
        @RequestParam(required = false) String word, 
        @RequestParam(required = false) String kana, 
        @RequestParam(required = false) String romaji
        ) {
        Word wordData = wordService.searchDataByKeywords(word, kana, romaji);
        if (wordData != null) {
            return ResponseEntity.status(HttpStatus.OK).body(wordData);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
