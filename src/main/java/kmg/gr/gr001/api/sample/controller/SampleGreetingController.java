package kmg.gr.gr001.api.sample.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kmg.gr.gr001.api.sample.dto.SampleGreetingResponse;
import kmg.gr.gr001.domain.sample.service.SampleGreetingService;

/**
 * サンプル挨拶用コントローラ<br>
 * <p>
 * ドメイン層のサンプルサービスを呼び、配線確認用の応答を返します。
 * </p>
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.1.0
 */
@RestController
@RequestMapping("/api/sample")
public class SampleGreetingController {

    /**
     * サンプル挨拶サービス
     *
     * @since 0.1.0
     */
    private final SampleGreetingService sampleGreetingService;

    /**
     * コンストラクタ
     *
     * @param sampleGreetingService
     *                              サンプル挨拶サービス
     *
     * @since 0.1.0
     */
    public SampleGreetingController(final SampleGreetingService sampleGreetingService) {

        this.sampleGreetingService = sampleGreetingService;

    }

    /**
     * サンプル挨拶メッセージを返す
     *
     * @return サンプル挨拶応答
     *
     * @since 0.1.0
     */
    @GetMapping
    public ResponseEntity<SampleGreetingResponse> greet() {

        /* 戻り値の宣言 */
        final ResponseEntity<SampleGreetingResponse> result;

        /* ドメインサービスの呼び出し */
        final String message = this.sampleGreetingService.greet();

        /* 応答の生成 */
        final SampleGreetingResponse body = new SampleGreetingResponse(message);
        result = ResponseEntity.ok(body);

        return result;

    }

}
