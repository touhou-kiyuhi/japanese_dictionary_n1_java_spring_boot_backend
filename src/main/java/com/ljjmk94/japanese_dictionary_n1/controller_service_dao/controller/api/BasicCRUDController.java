package com.ljjmk94.japanese_dictionary_n1.controller_service_dao.controller.api;

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
import org.springframework.web.bind.annotation.RestController;

import com.ljjmk94.japanese_dictionary_n1.controller_service_dao.service.WordService;
import com.ljjmk94.japanese_dictionary_n1.format.Word;
import com.ljjmk94.japanese_dictionary_n1.format.WordRequest;

@RestController
@RequestMapping("/api")
public class BasicCRUDController {
    
    @Autowired
    private WordService wordService;

    // CREATE 
    @PostMapping("/data")
    public ResponseEntity<Word> saveWord(@RequestBody WordRequest wordRequest) {
        Integer wordId = wordService.saveData(wordRequest);
        // 回傳 Response 
        Word word = wordService.getDataById(wordId);
        return ResponseEntity.status(HttpStatus.CREATED).body(word);
    }

    // READ 
    // Data By Id 
    // 路徑
    @GetMapping("/data/{wordId}")
    public ResponseEntity<Word> getDataById(@PathVariable Integer wordId) {
        Word word = wordService.getDataById(wordId);
        // 是否存在
        if (word != null) {
            return ResponseEntity.status(HttpStatus.OK).body(word);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // UPDATE 
    @PutMapping("/data/{wordId}")
    public ResponseEntity<Word> updateDataByKeywords(@PathVariable Integer wordId, @RequestBody WordRequest wordRequest) {
        // 檢查 word 是否存在
        Word word = wordService.getDataById(wordId);
        if (word == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        // 修改 word 的數據
        wordService.updateData(wordId, wordRequest);
        
        Word updatedWord = wordService.getDataById(wordId);
        
        return ResponseEntity.status(HttpStatus.OK).body(updatedWord);
    }

    // DELETE 
    @DeleteMapping("/data/{wordId}")
    public ResponseEntity<?> deleteDataById(@PathVariable Integer wordId) {
        wordService.deleteDataById(wordId);
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
