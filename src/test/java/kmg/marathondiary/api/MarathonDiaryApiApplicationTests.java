package kmg.marathondiary.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * MarathonDiaryApiApplication のテスト
 *
 * @author KenichiroArai
 * @since 0.1.0
 * @version 0.1.0
 */
@SuppressWarnings({
	"nls", "static-method"
})
@SpringBootTest
public class MarathonDiaryApiApplicationTests {

	/**
	 * アプリケーションコンテキスト
	 */
	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * コンテキスト起動のテスト - 正常系:アプリケーションコンテキストがロードされる場合
	 */
	@Test
	public void testContextLoads_normalLoaded() {

		/* 期待値の定義 */
		final boolean expectedLoaded = true;

		/* 準備 */

		/* テスト対象の実行 */

		/* 検証の準備 */
		final boolean actualLoaded = this.applicationContext != null;

		/* 検証の実施 */
		Assertions.assertEquals(expectedLoaded, actualLoaded, "アプリケーションコンテキストがロードされていません");

	}

}
