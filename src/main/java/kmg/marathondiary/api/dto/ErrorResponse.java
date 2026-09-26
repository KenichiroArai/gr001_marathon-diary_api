package kmg.marathondiary.api.dto;

import java.time.Instant;

/**
 * 統一エラー応答
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.1.0
 *
 * @param timestamp
 *                  発生時刻（UTC）
 * @param status
 *                  HTTP ステータスコード
 * @param error
 *                  エラー種別
 * @param message
 *                  エラーメッセージ
 * @param path
 *                  リクエストパス
 */
public record ErrorResponse(Instant timestamp, int status, String error, String message, String path) {
    // 処理なし
}
