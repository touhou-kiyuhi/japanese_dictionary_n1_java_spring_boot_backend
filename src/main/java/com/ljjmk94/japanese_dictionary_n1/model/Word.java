package com.ljjmk94.japanese_dictionary_n1.model;

import java.util.Date;

public class Word {

    private int id;
    private String word;
    private String kana;
    private String romaji;
    private String meaning;
    private String sentence;
    private String sentenceMeaning;
    private Date createdDate;
    private Date lastModifiedDate;

    public Word() {}
    
    public Word(
        int id, String word, String kana, String romaji, String meaning, 
        String sentence, String sentenceMeaning, 
        Date createdDate, Date lastModifiedDate
    ) {
        this.id = id;
        this.word = word;
        this.kana = kana;
        this.romaji = romaji;
        this.meaning = meaning;
        this.sentence = sentence;
        this.sentenceMeaning = sentenceMeaning;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }

    public String getKana() {
        return kana;
    }
    public void setKana(String kana) {
        this.kana = kana;
    }

    public String getRomaji() {
        return romaji;
    }
    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }

    public String getMeaning() {
        return meaning;
    }
    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getSentence() {
        return sentence;
    }
    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getSentenceMeaning() {
        return sentenceMeaning;
    }
    public void setSentenceMeaning(String sentenceMeaning) {
        this.sentenceMeaning = sentenceMeaning;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
    
}
