package kmg.gr.gr001.api.sample.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import kmg.gr.gr001.api.sample.dto.SampleGreetingResponse;
import kmg.gr.gr001.domain.sample.service.SampleGreetingService;

/**
 * SampleGreetingController のテスト
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.1.0
 */
@SuppressWarnings({
    "nls",
})
@WebMvcTest(controllers = SampleGreetingController.class)
public class SampleGreetingControllerTest {

    /**
     * MockMvc
     *
     * @since 0.1.0
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * サンプル挨拶サービス（モック）
     *
     * @since 0.1.0
     */
    @MockitoBean
    private SampleGreetingService sampleGreetingService;

    /**
     * デフォルトコンストラクタ
     *
     * @since 0.1.0
     */
    public SampleGreetingControllerTest() {

        // 処理なし
    }

    /**
     * コンストラクタのテスト - 正常系:インスタンスを生成できる場合
     *
     * @since 0.1.0
     */
    @Test
    public void testConstructor_normal() {

        /* 期待値の定義 */

        /* 準備 */
        final SampleGreetingService testService = Mockito.mock(SampleGreetingService.class);

        /* テスト対象の実行 */
        final SampleGreetingController testTarget = new SampleGreetingController(testService);

        /* 検証の準備 */

        /* 検証の実施 */
        Assertions.assertNotNull(testTarget, "インスタンスが生成されていません");

    }

    /**
     * greet メソッドのテスト - 正常系:サンプル挨拶が返る場合
     *
     * @throws Exception
     *                   リクエスト実行時に例外が発生した場合
     *
     * @since 0.1.0
     */
    @Test
    public void testGreet_normalMessage() throws Exception {

        /* 期待値の定義 */
        final int    expectedStatus  = 200;
        final String expectedMessage = "Hello from sample domain";

        /* 準備 */
        Mockito.when(this.sampleGreetingService.greet()).thenReturn(expectedMessage);

        /* テスト対象の実行 */
        final MvcResult testResult
            = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/sample").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(expectedMessage)).andReturn();

        /* 検証の準備 */
        final int actualStatus = testResult.getResponse().getStatus();

        /* 検証の実施 */
        Assertions.assertEquals(expectedStatus, actualStatus, "HTTPステータスが一致しません");

    }

    /**
     * greet メソッドのテスト - 正常系:サービス経由で応答が生成される場合
     *
     * @since 0.1.0
     */
    @Test
    public void testGreet_normalDirectCall() {

        /* 期待値の定義 */
        final String expectedMessage = "Hello from sample domain";

        /* 準備 */
        final SampleGreetingService testService = Mockito.mock(SampleGreetingService.class);
        Mockito.when(testService.greet()).thenReturn(expectedMessage);
        final SampleGreetingController testTarget = new SampleGreetingController(testService);

        /* テスト対象の実行 */
        final ResponseEntity<SampleGreetingResponse> testResult = testTarget.greet();

        /* 検証の準備 */
        final String actualMessage = testResult.getBody().message();

        /* 検証の実施 */
        Assertions.assertEquals(expectedMessage, actualMessage, "メッセージが一致しません");

    }

}
