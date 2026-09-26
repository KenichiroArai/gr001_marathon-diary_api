package kmg.gr.gr001.api.dto;

import java.time.Instant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * ErrorResponse のテスト
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.1.0
 */
@SuppressWarnings({
    "nls", "static-method",
})
public class ErrorResponseTest {

    /**
     * デフォルトコンストラクタ
     *
     * @since 0.1.0
     */
    public ErrorResponseTest() {

        // 処理なし
    }

    /**
     * error メソッドのテスト - 正常系:エラー種別が返る場合
     *
     * @since 0.1.0
     */
    @Test
    public void testError_normalValue() {

        /* 期待値の定義 */
        final String expectedError = "Bad Request";

        /* 準備 */
        final ErrorResponse testTarget
            = new ErrorResponse(Instant.parse("2026-01-01T00:00:00Z"), 400, expectedError, "msg", "/api/test");

        /* テスト対象の実行 */
        final String actualError = testTarget.error();

        /* 検証の実施 */
        Assertions.assertEquals(expectedError, actualError, "エラー種別が一致しません");

    }

    /**
     * message メソッドのテスト - 正常系:エラーメッセージが返る場合
     *
     * @since 0.1.0
     */
    @Test
    public void testMessage_normalValue() {

        /* 期待値の定義 */
        final String expectedMessage = "name: must not be blank";

        /* 準備 */
        final ErrorResponse testTarget
            = new ErrorResponse(Instant.parse("2026-01-01T00:00:00Z"), 400, "Bad Request", expectedMessage, "/api/test");

        /* テスト対象の実行 */
        final String actualMessage = testTarget.message();

        /* 検証の実施 */
        Assertions.assertEquals(expectedMessage, actualMessage, "エラーメッセージが一致しません");

    }

    /**
     * path メソッドのテスト - 正常系:リクエストパスが返る場合
     *
     * @since 0.1.0
     */
    @Test
    public void testPath_normalValue() {

        /* 期待値の定義 */
        final String expectedPath = "/api/test";

        /* 準備 */
        final ErrorResponse testTarget
            = new ErrorResponse(Instant.parse("2026-01-01T00:00:00Z"), 404, "Not Found", "msg", expectedPath);

        /* テスト対象の実行 */
        final String actualPath = testTarget.path();

        /* 検証の実施 */
        Assertions.assertEquals(expectedPath, actualPath, "リクエストパスが一致しません");

    }

    /**
     * status メソッドのテスト - 正常系:HTTP ステータスコードが返る場合
     *
     * @since 0.1.0
     */
    @Test
    public void testStatus_normalValue() {

        /* 期待値の定義 */
        final int expectedStatus = 500;

        /* 準備 */
        final ErrorResponse testTarget = new ErrorResponse(Instant.parse("2026-01-01T00:00:00Z"), expectedStatus,
            "Internal Server Error", "msg", "/api/test");

        /* テスト対象の実行 */
        final int actualStatus = testTarget.status();

        /* 検証の実施 */
        Assertions.assertEquals(expectedStatus, actualStatus, "HTTP ステータスコードが一致しません");

    }

    /**
     * timestamp メソッドのテスト - 正常系:発生時刻が返る場合
     *
     * @since 0.1.0
     */
    @Test
    public void testTimestamp_normalValue() {

        /* 期待値の定義 */
        final Instant expectedTimestamp = Instant.parse("2026-01-01T00:00:00Z");

        /* 準備 */
        final ErrorResponse testTarget
            = new ErrorResponse(expectedTimestamp, 400, "Bad Request", "msg", "/api/test");

        /* テスト対象の実行 */
        final Instant actualTimestamp = testTarget.timestamp();

        /* 検証の実施 */
        Assertions.assertEquals(expectedTimestamp, actualTimestamp, "発生時刻が一致しません");

    }

}
