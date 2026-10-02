# marathon-diary api

マラソン日記の REST API（Controller / DTO）である。

## 概要

本リポジトリは、マラソン日記（marathon-diary）の REST API の Controller / DTO をまとめるライブラリである。
起動クラスは持たず、起動は `gr001_marathon-diary_api-boot` で行う。

```text
api-boot ──> api（本リポジトリ） ──> domain
   └──────> db-postgresql ──> domain
```

- 本リポジトリは domain にだけ依存し、DB 実装（db-xxx）の存在を知らない
- どの DB を使うかは api-boot が決める

技術スタック:

- Java 25 / Spring Boot 4.1.1（ライブラリ jar）
- Maven
- REST API（Spring MVC）
- 基盤ライブラリ: kmg-core / kmg-fund

## 必要環境

- JDK 25
- Maven 3.6.3 以降
- kmg-core / kmg-fund（ローカル `mvn install` または GitHub Packages）
- `gr001_marathon-diary_domain`（ローカル `mvn install`）

## ビルド

```bash
# 事前に domain を mvn install しておく
cd ../gr001_marathon-diary_domain && mvn install

# テスト（JaCoCo レポート生成 + 行/分岐カバレッジ 100% チェック）
cd ../gr001_marathon-diary_api && mvn test

# ローカルリポジトリへインストール（api-boot から利用するため）
mvn install
```

カバレッジレポートは `target/site/jacoco/index.html` に出力される。
`target/jacoco.exec` は Eclipse のカバレッジ表示と共有できる。

成果物は `target/gr001_marathon-diary_api-0.1.0.jar`（実行可能 fat jar ではない）。

## 起動

本リポジトリ単体では起動しない。`gr001_marathon-diary_api-boot` の README の手順で起動する。

## エンドポイント

| メソッド | パス | 内容 |
| --- | --- | --- |
| GET | `/api/health` | ヘルスチェック |
| GET | `/api/sample` | サンプル挨拶（DB の `sample_greeting` テーブルのメッセージ） |

`/api/sample` は DB → domain → api → 画面の配線確認用サンプルである。

開発用 CORS（`app.cors.allowed-origins`）は api-boot の `application.yml` で設定する。

本 API は REST 専用であり、フロントの静的ファイルは同梱しない。

## Eclipse の設定

Eclipse のビルド・パス（`.classpath`）は Git で管理している。
内容は m2e が `pom.xml` から生成する標準の構成（JRE は `JavaSE-25`、出力先は `target/classes` / `target/test-classes`）で、PC 固有の絶対パスは含まない。
`.settings/` と `bin/` は Git で管理しない。

- `.classpath` は手で編集しない。`pom.xml` を変更した場合は「Maven」→「プロジェクトの更新」で反映する
- 「プロジェクトの更新」の実行後は `.classpath` に差分が出ていないことを確認する（差分が出た場合は意図した変更か確認してからコミットする）

## ディレクトリ構成

```text
src/main/java/kmg/gr/gr001/api/
  config/              # CORS など
  controller/          # REST コントローラ
  dto/                 # リクエスト / レスポンス
  exception/           # 例外ハンドリング
  sample/              # サンプル（配線確認用）
    controller/        # SampleGreetingController（GET /api/sample）
    dto/               # SampleGreetingResponse
src/test/java/kmg/gr/gr001/api/
  ApiTestApplication.java   # テスト専用の Spring Boot 設定クラス（@WebMvcTest 用）
src/test/resources/
  application.yml           # テスト用設定
```

## ライセンス

[MIT License](./LICENSE)
