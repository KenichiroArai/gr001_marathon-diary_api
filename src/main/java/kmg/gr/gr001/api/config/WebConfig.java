package kmg.gr.gr001.api.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC（CORS）設定
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.1.0
 */
@Configuration
@EnableConfigurationProperties(CorsProperties.class)
public class WebConfig implements WebMvcConfigurer {

    /**
     * CORS 設定プロパティ
     */
    private final CorsProperties corsProperties;

    /**
     * コンストラクタ
     *
     * @param corsProperties
     *                       CORS 設定プロパティ
     */
    public WebConfig(final CorsProperties corsProperties) {

        this.corsProperties = corsProperties;

    }

    /**
     * CORS マッピングを登録する
     *
     * @param registry
     *                 CORS レジストリ
     */
    @SuppressWarnings("nls")
    @Override
    public void addCorsMappings(final CorsRegistry registry) {

        /* 許可オリジンの解決 */
        final String[] allowedOrigins = this.corsProperties.allowedOrigins() == null ? new String[0]
            : this.corsProperties.allowedOrigins().toArray(String[]::new);

        /* CORS の登録 */
        registry.addMapping("/api/**").allowedOrigins(allowedOrigins)
            .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS").allowedHeaders("*")
            .allowCredentials(true);

    }

}
