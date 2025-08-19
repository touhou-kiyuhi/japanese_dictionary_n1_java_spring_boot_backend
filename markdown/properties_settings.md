# properties 設定
## application.properties 檔內容
* 檔案路徑：`src/main/resources/application.properties`
    ```properties
    spring.application.name=japanese_dictionary_n1

    # MySQL 資料庫設定
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://localhost:3306/jlpt_n1?serverTimezone=Asia/Taipei&characterEncoding=utf-8
    # [username] 填入 mysql 使用者名稱
    spring.datasource.username=[username]
    # [username] 填入 mysql 密碼
    spring.datasource.password=[password]

    # Spring Boot 的時間格式
    spring.jackson.time-zone=GMT+8
    spring.jackson.date-format=yyyy-MM-dd HH:mm:ss
    ```