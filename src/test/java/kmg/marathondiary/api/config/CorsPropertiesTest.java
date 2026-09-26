package kmg.marathondiary.api.config;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * CorsProperties のテスト
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
public class CorsPropertiesTest {

    /**
     * デフォルトコンストラクタ
     *
     * @since 0.1.0
     */
    public CorsPropertiesTest() {

        // 処理なし
    }

    /**
     * allowedOrigins メソッドのテスト - 正常系:許可オリジン一覧が返る場合
     *
     * @since 0.1.0
     */
    @Test
    public void testAllowedOrigins_normalList() {

        /* 期待値の定義 */
        final List<String> expectedAllowedOrigins = List.of("http://localhost:3000", "http://127.0.0.1:3000");

        /* 準備 */
        final CorsProperties testTarget = new CorsProperties(expectedAllowedOrigins);

        /* テスト対象の実行 */
        final List<String> actualAllowedOrigins = testTarget.allowedOrigins();

        /* 検証の実施 */
        Assertions.assertEquals(expectedAllowedOrigins, actualAllowedOrigins, "許可オリジン一覧が一致しません");

    }

    /**
     * allowedOrigins メソッドのテスト - 準正常系:許可オリジンが null の場合
     *
     * @since 0.1.0
     */
    @Test
    public void testAllowedOrigins_semiNull() {

        /* 期待値の定義 */
        final List<String> expectedAllowedOrigins = null;

        /* 準備 */
        final CorsProperties testTarget = new CorsProperties(null);

        /* テスト対象の実行 */
        final List<String> actualAllowedOrigins = testTarget.allowedOrigins();

        /* 検証の実施 */
        Assertions.assertEquals(expectedAllowedOrigins, actualAllowedOrigins, "許可オリジンが null ではありません");

    }

}
