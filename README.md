# japanese dictionary n1
## 更新日誌
* 2025/08/19
    * **first commit**
    * **實現 日語字典 Basic CRUD 功能：(BasicCRUDController.java)**
        * Create
        * Read
        * Update
        * Delete
    * **實現 日語字典 查詢功能：(OthersCRUDController.java)**
        * 取得所有資料
        * 根據 id 取得指定資料、根據 word 、 kana 、 romaji 參數查詢相關資料
    * **新增 git_commit_messages 資料夾，實現 git commit 生成**
    * **新增 jlpt_n1 備份資料夾**
---
## 資料庫
### [資料庫初始化](/markdown/database_init.md)
### [資料庫額外新增](/markdown/database_new.md)
---
## Spring Boot 打包 Jar
### [Maven 專案打包 .jar](/markdown/maven_to_jar.md)
---
## 參考資料
### git commit
* [偉大的 Git commit message rules](https://hackmd.io/@howhow/git_commit#%E5%81%89%E5%A4%A7%E7%9A%84-Git-commit-message-rules)
* [Git Commit Message 規範](https://codeewander.github.io/docs/git-commit)
* [Git Commit Message 這樣寫會更好，替專案引入規範與範例](https://wadehuanglearning.blogspot.com/2019/05/commit-commit-commit-why-what-commit.html)
### MySQL
* [以 MySQL 為例解釋外鍵（Foreign Key）](https://b-l-u-e-b-e-r-r-y.github.io/post/ForeignKey/)