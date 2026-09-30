package kmg.gr.gr001.api;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * テスト専用の Spring Boot 設定クラス<br>
 * <p>
 * 本モジュールは起動クラスを持たないライブラリのため、{@code @WebMvcTest} が参照する設定クラスとしてテストにのみ配置します。
 * </p>
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.1.0
 */
@SpringBootApplication
public class ApiTestApplication {

    /**
     * デフォルトコンストラクタ
     *
     * @since 0.1.0
     */
    public ApiTestApplication() {

        // 処理なし
    }

}
