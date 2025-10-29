package com.ljjmk94.japanese_dictionary_n1.exception;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ljjmk94.japanese_dictionary_n1.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 400 - 請求錯誤
    @ExceptionHandler({
        IllegalArgumentException.class,
        MethodArgumentNotValidException.class,
        MissingServletRequestParameterException.class,
        HttpMessageNotReadableException.class
    })
    public ResponseEntity<ApiResponse> handleBadRequest(Exception e) {
        ApiResponse response = new ApiResponse("Bad Request: " + e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // 401 - 驗證失敗
    // @ExceptionHandler(AuthenticationException.class)
    // public ResponseEntity<ApiResponse> handleUnauthorized(AuthenticationException e) {
    //     ApiResponse response = new ApiResponse("Unauthorized: " + e.getMessage(), null);
    //     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    // }

    // 403 - 沒有權限
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse> handleForbidden(AccessDeniedException e) {
        ApiResponse response = new ApiResponse("Forbidden: " + e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    // 404 - 找不到資源
    @ExceptionHandler({NoSuchElementException.class, WordNotFoundException.class})
    public ResponseEntity<ApiResponse> handleNotFound(Exception e) {
        ApiResponse response = new ApiResponse("Not Found: " + e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    
    // 409 - 衝突（例如資料重複）
    @ExceptionHandler({IllegalStateException.class, DuplicateKeyException.class})
    public ResponseEntity<ApiResponse> handleDuplicateWord(IllegalStateException e) {
        ApiResponse response = new ApiResponse("Conflict: " + e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    // 500 - 系統錯誤
    @ExceptionHandler({
        SQLException.class, DataAccessException.class, IOException.class, Exception.class
    })
    public ResponseEntity<ApiResponse> handleServerError(Exception ex) {
        ApiResponse response = new ApiResponse("Internal Server Error: " + ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
