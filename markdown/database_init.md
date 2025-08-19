# 資料庫初始化
## 使用 MySQL
### 初始
```sql
CREATE DATABASE jlpt_n1;
```
```sql
CREATE TABLE jlpt_n1.n1_word (
    id                 INT          NOT NULL PRIMARY KEY AUTO_INCREMENT, 
    word               VARCHAR(256) NOT NULL, 
    meaning            TEXT         NOT NULL,
    created_date       TIMESTAMP    NOT NULL,
    last_modified_date TIMESTAMP    NOT NULL
);
CREATE TABLE jlpt_n1.n1_kana_romaji (
    id      INT          NOT NULL PRIMARY KEY AUTO_INCREMENT, 
    word_id INT          NOT NULL,
    kana    VARCHAR(256) NOT NULL,
    romaji  VARCHAR(256) NOT NULL, 
    FOREIGN KEY (word_id) REFERENCES jlpt_n1.n1_word(id)
);
CREATE TABLE jlpt_n1.n1_sentence (
    id               INT  NOT NULL PRIMARY KEY AUTO_INCREMENT, 
    word_id          INT  NOT NULL, 
    sentence         TEXT NOT NULL, 
    sentence_meaning TEXT NOT NULL, 
    FOREIGN KEY (word_id) REFERENCES jlpt_n1.n1_word(id)
);
```
### 第一筆資料
```sql
USE jlpt_n1;
INSERT INTO n1_word (word, meaning, created_date, last_modified_date) VALUES ('亜', '亞，次；(化)亞(表示無機酸中氧原子較少)；用在外語的音譯；亞細亞，亞洲', '2025-03-09 10:55:00', '2025-03-09 10:55:00');
INSERT INTO n1_kana_romaji (word_id, kana, romaji) VALUES ((SELECT id FROM n1_word WHERE word = '亜'), 'あ', 'a');
INSERT INTO n1_sentence (word_id, sentence, sentence_meaning) VALUES ((SELECT id FROM n1_word WHERE word = '亜'), 'さすが亜熱帯台湾だ、暑いといったらない。', '真不愧是亞熱帶的台灣，熱得嚇人呀。');
```
### 查詢第一筆資料
```sql
USE jlpt_n1;
SELECT n1_w.word, n1_kr.kana, n1_kr.romaji, n1_w.meaning, n1_s.sentence, n1_s.sentence_meaning, n1_w.created_date, n1_w.last_modified_date
FROM n1_word n1_w
JOIN n1_kana_romaji n1_kr ON n1_w.id = n1_kr.word_id
LEFT JOIN n1_sentence n1_s ON n1_w.id = n1_s.word_id
WHERE n1_w.word = '亜';
```