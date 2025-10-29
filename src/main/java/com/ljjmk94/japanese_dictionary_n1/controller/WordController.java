package com.ljjmk94.japanese_dictionary_n1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ljjmk94.japanese_dictionary_n1.dto.WordDto;
import com.ljjmk94.japanese_dictionary_n1.model.Word;
import com.ljjmk94.japanese_dictionary_n1.request.WordRequest;
import com.ljjmk94.japanese_dictionary_n1.response.ApiResponse;
import com.ljjmk94.japanese_dictionary_n1.service.WordService;

@RestController
@RequestMapping("/api/v1/words")
public class WordController {
    
    @Autowired
    private WordService wordService;

    // Basic CRUD 
    // CREATE 
    @PostMapping
    public ResponseEntity<ApiResponse> createWord(@RequestBody WordRequest wordRequest) {
        Integer wordId = wordService.createWord(wordRequest);
        // 回傳 Response 
        Word word = wordService.getWordById(wordId);
        WordDto wordDto = wordService.convertToWordDto(word);
        ApiResponse response = new ApiResponse("Word Created Successfully!", wordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    // READ 
    // Data By Id 
    // 路徑
    @GetMapping("/{wordId}")
    public ResponseEntity<ApiResponse> getWordById(@PathVariable Integer wordId) {
        Word word = wordService.getWordById(wordId);
        // 是否存在
        if (word == null) {
            ApiResponse response = new ApiResponse("Word Not Found!", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        WordDto wordDto = wordService.convertToWordDto(word);
        ApiResponse response = new ApiResponse("Word Fetched Successfully!", wordDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    // UPDATE 
    @PutMapping("/{wordId}")
    public ResponseEntity<ApiResponse> updateWord(@PathVariable Integer wordId, @RequestBody WordRequest wordRequest) {
        // 檢查 word 是否存在
        Word word = wordService.getWordById(wordId);
        if (word == null) {
            ApiResponse response = new ApiResponse("Word Not Found!", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        // 修改 word 的數據
        wordService.updateWord(wordId, wordRequest);
        Word updatedWord = wordService.getWordById(wordId);
        WordDto wordDto = wordService.convertToWordDto(updatedWord);
        ApiResponse response = new ApiResponse("Word Updated Successfully!", wordDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    // DELETE 
    @DeleteMapping("/{wordId}")
    public ResponseEntity<ApiResponse> deleteWord(@PathVariable Integer wordId) {
        // 檢查 word 是否存在
        Word word = wordService.getWordById(wordId);
        if (word == null) {
            ApiResponse response = new ApiResponse("Word Not Found!", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        wordService.deleteWord(wordId);
        ApiResponse response = new ApiResponse("Word Deleted Successfully!", null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Others 
    // READ 
    // All Data 
    @GetMapping
    public ResponseEntity<ApiResponse> getAllWords() {
        List<Word> wordList = wordService.getAllWords();
        List<WordDto> wordDtos = wordService.convertToWordDtoList(wordList);
        ApiResponse response = new ApiResponse("Words Fetched Successfully!", wordDtos);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    // Data By Word, Kana, Romaji
    // 參數
    @GetMapping("/search")
    public ResponseEntity<ApiResponse> getAllWordsByKeywords(
        @RequestParam(required = false) String word, 
        @RequestParam(required = false) String kana, 
        @RequestParam(required = false) String romaji
    ) {
        List<Word> wordList = wordService.getAllWordsByKeywords(word, kana, romaji);
        List<WordDto> wordDtos = wordService.convertToWordDtoList(wordList);
        ApiResponse response = new ApiResponse("Words Fetched Successfully!", wordDtos);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/search/one")
    public ResponseEntity<ApiResponse> getWordByKeywords(
        @RequestParam(required = false) String word, 
        @RequestParam(required = false) String kana, 
        @RequestParam(required = false) String romaji
    ) {
        Word wordData = wordService.getWordByKeywords(word, kana, romaji);
        if (wordData == null) {
            ApiResponse response = new ApiResponse("Word Not Found!", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        WordDto wordDto = wordService.convertToWordDto(wordData);
        ApiResponse response = new ApiResponse("Word Fetched Successfully!", wordDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
