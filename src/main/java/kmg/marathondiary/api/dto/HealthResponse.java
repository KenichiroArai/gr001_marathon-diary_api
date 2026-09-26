package kmg.marathondiary.api.dto;

/**
 * ヘルスチェック応答
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.1.0
 *
 * @param status
 *                稼働状態
 * @param service
 *                サービス名
 */
public record HealthResponse(String status, String service) {
    // 処理なし
}
