package kmg.marathondiary.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * マラソン日記 REST API の起動クラス
 *
 * @author KenichiroArai
 * @since 0.1.0
 * @version 0.1.0
 */
@SpringBootApplication
public class MarathonDiaryApiApplication {

	/**
	 * アプリケーションを起動する
	 *
	 * @param args
	 *               コマンドライン引数
	 */
	public static void main(final String[] args) {

		SpringApplication.run(MarathonDiaryApiApplication.class, args);

	}

}
