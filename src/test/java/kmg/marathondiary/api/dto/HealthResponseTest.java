package kmg.marathondiary.api.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * HealthResponse のテスト
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
public class HealthResponseTest {

    /**
     * デフォルトコンストラクタ
     *
     * @since 0.1.0
     */
    public HealthResponseTest() {

        // 処理なし
    }

    /**
     * service メソッドのテスト - 正常系:サービス名が返る場合
     *
     * @since 0.1.0
     */
    @Test
    public void testService_normalValue() {

        /* 期待値の定義 */
        final String expectedService = "marathon-diary-api";

        /* 準備 */
        final HealthResponse testTarget = new HealthResponse("UP", expectedService);

        /* テスト対象の実行 */
        final String actualService = testTarget.service();

        /* 検証の実施 */
        Assertions.assertEquals(expectedService, actualService, "サービス名が一致しません");

    }

    /**
     * status メソッドのテスト - 正常系:稼働状態が返る場合
     *
     * @since 0.1.0
     */
    @Test
    public void testStatus_normalValue() {

        /* 期待値の定義 */
        final String expectedStatus = "UP";

        /* 準備 */
        final HealthResponse testTarget = new HealthResponse(expectedStatus, "marathon-diary-api");

        /* テスト対象の実行 */
        final String actualStatus = testTarget.status();

        /* 検証の実施 */
        Assertions.assertEquals(expectedStatus, actualStatus, "稼働状態が一致しません");

    }

}
