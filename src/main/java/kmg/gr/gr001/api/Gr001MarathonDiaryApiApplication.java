package kmg.gr.gr001.api;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * マラソン日記 REST API の起動クラス
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.1.0
 */
@SpringBootApplication
public class Gr001MarathonDiaryApiApplication {

    /**
     * エントリポイント
     *
     * @since 0.1.0
     *
     * @param args
     *             引数
     */
    @SuppressWarnings({
        "resource",
    })
    public static void main(final String[] args) {

        // SpringApplicationの設定
        final SpringApplication application = new SpringApplication(Gr001MarathonDiaryApiApplication.class);
        final Properties        properties  = new Properties();
        application.setDefaultProperties(properties);

        application.run(args);

    }

    /**
     * コンストラクタ
     *
     * @since 0.1.0
     */
    public Gr001MarathonDiaryApiApplication() {

        // 処理なし
    }

}
