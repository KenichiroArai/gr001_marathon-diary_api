package kmg.marathondiary.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kmg.marathondiary.api.dto.HealthResponse;

/**
 * ヘルスチェック用コントローラ
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.1.0
 */
@RestController
@RequestMapping("/api/health")
public class HealthController {

    /**
     * サービス名
     */
    @SuppressWarnings("nls")
    private static final String SERVICE_NAME = "marathon-diary-api";

    /**
     * 稼働状態（正常）
     */
    @SuppressWarnings("nls")
    private static final String STATUS_UP = "UP";

    /**
     * API の稼働状態を返す
     *
     * @return ヘルスチェック応答
     */
    @SuppressWarnings("static-method")
    @GetMapping
    public ResponseEntity<HealthResponse> health() {

        /* 戻り値の宣言 */
        final ResponseEntity<HealthResponse> result;

        /* 応答の生成 */
        final HealthResponse body = new HealthResponse(HealthController.STATUS_UP, HealthController.SERVICE_NAME);
        result = ResponseEntity.ok(body);

        return result;

    }

}
