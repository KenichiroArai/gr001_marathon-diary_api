package kmg.gr.gr001.api.sample.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * SampleGreetingResponse のテスト
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
public class SampleGreetingResponseTest {

    /**
     * デフォルトコンストラクタ
     *
     * @since 0.1.0
     */
    public SampleGreetingResponseTest() {

        // 処理なし
    }

    /**
     * message メソッドのテスト - 正常系:メッセージが返る場合
     *
     * @since 0.1.0
     */
    @Test
    public void testMessage_normalValue() {

        /* 期待値の定義 */
        final String expectedMessage = "Hello from sample domain";

        /* 準備 */
        final SampleGreetingResponse testTarget = new SampleGreetingResponse(expectedMessage);

        /* テスト対象の実行 */
        final String actualMessage = testTarget.message();

        /* 検証の実施 */
        Assertions.assertEquals(expectedMessage, actualMessage, "メッセージが一致しません");

    }

}
