# 資料庫額外新增
## 使用 MySQL
### 新增一筆資料
```sql
USE jlpt_n1;
INSERT INTO n1_word (word, meaning, created_date, last_modified_date) VALUES ('相子', '不分勝負，不相上下', '2025-03-09 10:55:00', '2025-03-09 10:55:00');
INSERT INTO n1_kana_romaji (word_id, kana, romaji) VALUES ((SELECT id FROM n1_word WHERE word = '相子'), 'あいこ', 'aiko');
INSERT INTO n1_sentence (word_id, sentence, sentence_meaning) VALUES ((SELECT id FROM n1_word WHERE word = '相子'), 'じゃんけんで５回もあいこになった。', '猜拳連續五次都成了平手。');
```
### 新增五筆資料
```sql
USE jlpt_n1;

INSERT INTO n1_word (word, meaning, created_date, last_modified_date) VALUES ('愛想', '(接待客人的態度、表情等)親切；接待，款待；(在飲食店)算帳，客人付的錢', '2025-03-10 09:55:00', '2025-03-10 09:55:00');
INSERT INTO n1_kana_romaji (word_id, kana, romaji) VALUES ((SELECT id FROM n1_word WHERE word = '愛想'), 'あいそ、あいそう', 'aiso, aisou');
INSERT INTO n1_sentence (word_id, sentence, sentence_meaning) VALUES ((SELECT id FROM n1_word WHERE word = '愛想'), 'ここの女将はいつも愛想よく客を迎える。', '這家的老闆娘在顧客上門時總是笑臉迎人。');

INSERT INTO n1_word (word, meaning, created_date, last_modified_date) VALUES ('間柄', '(親屬、親戚等的)關係；來往關係，交情', '2025-03-16 20:40:56', '2025-03-16 20:40:56');
INSERT INTO n1_kana_romaji (word_id, kana, romaji) VALUES ((SELECT id FROM n1_word WHERE word = '間柄'), 'あいだがら', 'aidagara');
INSERT INTO n1_sentence (word_id, sentence, sentence_meaning) VALUES ((SELECT id FROM n1_word WHERE word = '間柄'), '山田さんとは先輩後輩の間柄です。', '我跟山田先生是學長與學弟的關係。');

INSERT INTO n1_word (word, meaning, created_date, last_modified_date) VALUES ('相次ぐ、相継ぐ', '(文)接二連三，連續不斷', '2025-03-16 20:45:11', '2025-03-16 20:45:11');
INSERT INTO n1_kana_romaji (word_id, kana, romaji) VALUES ((SELECT id FROM n1_word WHERE word = '相次ぐ、相継ぐ'), 'あいつぐ', 'aitsugu');
INSERT INTO n1_sentence (word_id, sentence, sentence_meaning) VALUES ((SELECT id FROM n1_word WHERE word = '相次ぐ、相継ぐ'), '今年は相次ぐ災難に見舞われた。', '今年遭逢接二連三的天災人禍。');

INSERT INTO n1_word (word, meaning, created_date, last_modified_date) VALUES ('合間', '(事物中間的)空隙，空閒時間；餘暇', '2025-03-16 20:58:34', '2025-03-16 20:58:34');
INSERT INTO n1_kana_romaji (word_id, kana, romaji) VALUES ((SELECT id FROM n1_word WHERE word = '合間'), 'あいま', 'aima');
INSERT INTO n1_sentence (word_id, sentence, sentence_meaning) VALUES ((SELECT id FROM n1_word WHERE word = '合間'), '仕事の合間を見て彼に連絡した。', '趁著工作的空檔時間聯絡了他。');

INSERT INTO n1_word (word, meaning, created_date, last_modified_date) VALUES ('敢えて', '敢；硬是，勉強；(下接否定)毫(不)，不見得', '2025-03-17 00:32:10', '2025-03-17 00:32:10');
INSERT INTO n1_kana_romaji (word_id, kana, romaji) VALUES ((SELECT id FROM n1_word WHERE word = '敢えて'), 'あえて', 'aete');
INSERT INTO n1_sentence (word_id, sentence, sentence_meaning) VALUES ((SELECT id FROM n1_word WHERE word = '敢えて'), '上司は無能だと思うが、あえて逆らわない。', '雖然認為上司沒有能力，但也不敢反抗。');
```