# marathon-diary api

マラソン日記の REST API である。

## 概要

本リポジトリは、マラソン日記（marathon-diary）の REST API をまとめる。

- Java 25 / Spring Boot 4.1.1
- Maven
- REST API（Spring MVC）
- 基盤ライブラリ: kmg-core / kmg-fund

## 必要環境

- JDK 25
- Maven 3.6.3 以降
- kmg-core / kmg-fund（ローカル `mvn install` または GitHub Packages）

## ビルド / 起動

```bash
# テスト（JaCoCo レポート生成 + 行/分岐カバレッジ 100% チェック）
mvn test

# 起動
mvn spring-boot:run
```

カバレッジレポートは `target/site/jacoco/index.html` に出力される。
`target/jacoco.exec` は Eclipse のカバレッジ表示と共有できる。

起動後のヘルスチェック:

```text
GET http://localhost:8080/api/health
```

開発用 CORS（`application.yml` の `app.cors.allowed-origins`）:

- 既定で `http://localhost:3000` / `http://127.0.0.1:3000` を許可（Next.js 開発サーバ向け）
- 別フロント（例: Vue の `5173`）を足す場合は同リストにオリジンを追加する

本 API は REST 専用であり、フロントの静的ファイルは同梱しない。

## ディレクトリ構成

```text
src/main/java/kmg/marathondiary/api/
  MarathonDiaryApiApplication.java
  config/              # CORS など
  controller/          # REST コントローラ
  dto/                 # リクエスト / レスポンス
  exception/           # 例外ハンドリング
src/main/resources/
  application.yml
src/test/java/         # テスト
```

## ライセンス

[MIT License](./LICENSE)
