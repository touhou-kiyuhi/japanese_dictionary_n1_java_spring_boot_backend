package com.ljjmk94.japanese_dictionary_n1.request;

public class WordRequest {

    private String word;
    private String kana;
    private String romaji;
    private String meaning;
    private String sentence;
    private String sentenceMeaning;

    public WordRequest() {}

    public WordRequest(
        String word, String kana, String romaji, String meaning, 
        String sentence, String sentenceMeaning
    ) {
        this.word = word;
        this.kana = kana;
        this.romaji = romaji;
        this.meaning = meaning;
        this.sentence = sentence;
        this.sentenceMeaning = sentenceMeaning;
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
    
}
