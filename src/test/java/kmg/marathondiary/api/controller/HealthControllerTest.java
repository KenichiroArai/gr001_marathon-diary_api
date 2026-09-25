package kmg.marathondiary.api.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * HealthController のテスト
 *
 * @author KenichiroArai
 * @since 0.1.0
 * @version 0.1.0
 */
@SuppressWarnings({
	"nls", "static-method"
})
@WebMvcTest(controllers = HealthController.class)
public class HealthControllerTest {

	/**
	 * MockMvc
	 */
	@Autowired
	private MockMvc mockMvc;

	/**
	 * health メソッドのテスト - 正常系:稼働状態が UP で返る場合
	 *
	 * @throws Exception
	 *                     リクエスト実行時に例外が発生した場合
	 */
	@Test
	public void testHealth_normalUp() throws Exception {

		/* 期待値の定義 */
		final int expectedStatus = 200;
		final String expectedStatusBody = "UP";
		final String expectedService = "marathon-diary-api";

		/* 準備 */

		/* テスト対象の実行 */
		final MvcResult testResult = this.mockMvc
				.perform(MockMvcRequestBuilders.get("/api/health").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(expectedStatusBody))
				.andExpect(MockMvcResultMatchers.jsonPath("$.service").value(expectedService)).andReturn();

		/* 検証の準備 */
		final int actualStatus = testResult.getResponse().getStatus();

		/* 検証の実施 */
		Assertions.assertEquals(expectedStatus, actualStatus, "HTTPステータスが一致しません");

	}

}
