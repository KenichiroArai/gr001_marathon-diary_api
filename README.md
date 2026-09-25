# marathon-diary api

マラソン日記の REST API である。

## 概要

本リポジトリは、マラソン日記（marathon-diary）の REST API をまとめる。

- Java 25 / Spring Boot 4.1.1
- Maven
- REST API（Spring MVC）

## 必要環境

- JDK 25
- Maven 3.6.3 以降

## ビルド / 起動

```bash
# テスト
mvn test

# 起動
mvn spring-boot:run
```

起動後のヘルスチェック:

```text
GET http://localhost:8080/api/health
```

## ディレクトリ構成

```text
src/main/java/kmg/marathondiary/api/
  MarathonDiaryApiApplication.java
  controller/          # REST コントローラ
  dto/                 # リクエスト / レスポンス
  exception/           # 例外ハンドリング
src/main/resources/
  application.yml
src/test/java/         # テスト
```

## ライセンス

[MIT License](./LICENSE)
