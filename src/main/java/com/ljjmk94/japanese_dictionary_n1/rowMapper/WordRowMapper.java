package com.ljjmk94.japanese_dictionary_n1.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ljjmk94.japanese_dictionary_n1.model.Word;

public class WordRowMapper implements RowMapper<Word> {

    @Override
    public Word mapRow(ResultSet rs, int rowNum) throws SQLException {
        Word word = new Word();

        word.setId(rs.getInt("id"));
        word.setWord(rs.getString("word")); 
        word.setKana(rs.getString("kana")); 
        word.setRomaji(rs.getString("romaji"));
        word.setMeaning(rs.getString("meaning")); 
        word.setSentence(rs.getString("sentence")); 
        word.setSentenceMeaning(rs.getString("sentence_meaning")); 
        word.setCreatedDate(rs.getTimestamp("created_date"));
        word.setLastModifiedDate(rs.getTimestamp("last_modified_date"));

        return word;
    }
    
}
