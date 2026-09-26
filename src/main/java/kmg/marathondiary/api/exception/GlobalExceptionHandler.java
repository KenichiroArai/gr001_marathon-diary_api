package kmg.marathondiary.api.exception;

import java.time.Instant;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import kmg.marathondiary.api.dto.ErrorResponse;

/**
 * API 全体の例外を統一エラー応答へ変換するハンドラ
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * エラー種別: Bad Request
     */
    @SuppressWarnings("nls")
    private static final String ERROR_BAD_REQUEST = "Bad Request";

    /**
     * エラー種別: Internal Server Error
     */
    @SuppressWarnings("nls")
    private static final String ERROR_INTERNAL_SERVER_ERROR = "Internal Server Error";

    /**
     * エラー種別: Not Found
     */
    @SuppressWarnings("nls")
    private static final String ERROR_NOT_FOUND = "Not Found";

    /**
     * フィールドエラーの区切り文字
     */
    @SuppressWarnings("nls")
    private static final String FIELD_ERROR_SEPARATOR = ": ";

    /**
     * 入力検証失敗時のデフォルトメッセージ
     */
    @SuppressWarnings("nls")
    private static final String MESSAGE_VALIDATION_FAILED = "Validation failed";

    /**
     * 統一エラー応答を組み立てる
     *
     * @param status
     *                HTTP ステータス
     * @param error
     *                エラー種別
     * @param message
     *                エラーメッセージ
     * @param request
     *                HTTP リクエスト
     *
     * @return 統一エラー応答
     */
    private static ResponseEntity<ErrorResponse> buildResponse(final HttpStatus status, final String error,
        final String message, final HttpServletRequest request) {

        /* 戻り値の宣言 */
        final ResponseEntity<ErrorResponse> result;

        /* ボディの生成 */
        final ErrorResponse body
            = new ErrorResponse(Instant.now(), status.value(), error, message, request.getRequestURI());
        result = ResponseEntity.status(status).body(body);

        return result;

    }

    /**
     * 想定外例外のハンドリング
     *
     * @param exception
     *                  発生した例外
     * @param request
     *                  HTTP リクエスト
     *
     * @return 統一エラー応答
     */
    @SuppressWarnings("static-method")
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(final Exception exception, final HttpServletRequest request) {

        /* 戻り値の宣言 */
        final ResponseEntity<ErrorResponse> result;

        /* エラー応答の生成 */
        result = GlobalExceptionHandler.buildResponse(HttpStatus.INTERNAL_SERVER_ERROR,
            GlobalExceptionHandler.ERROR_INTERNAL_SERVER_ERROR, exception.getMessage(), request);

        return result;

    }

    /**
     * 入力検証エラーのハンドリング
     *
     * @param exception
     *                  発生した例外
     * @param request
     *                  HTTP リクエスト
     *
     * @return 統一エラー応答
     */
    @SuppressWarnings("static-method")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(final MethodArgumentNotValidException exception,
        final HttpServletRequest request) {

        /* 戻り値の宣言 */
        final ResponseEntity<ErrorResponse> result;

        /* 先頭の検証メッセージを取得 */
        final String message = exception.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + GlobalExceptionHandler.FIELD_ERROR_SEPARATOR + error.getDefaultMessage())
            .findFirst().orElse(GlobalExceptionHandler.MESSAGE_VALIDATION_FAILED);

        /* エラー応答の生成 */
        result = GlobalExceptionHandler.buildResponse(HttpStatus.BAD_REQUEST, GlobalExceptionHandler.ERROR_BAD_REQUEST,
            message, request);

        return result;

    }

    /**
     * リクエストパスが見つからない場合のハンドリング
     *
     * @param exception
     *                  発生した例外
     * @param request
     *                  HTTP リクエスト
     *
     * @return 統一エラー応答
     */
    @SuppressWarnings("static-method")
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResourceFound(final NoResourceFoundException exception,
        final HttpServletRequest request) {

        /* 戻り値の宣言 */
        final ResponseEntity<ErrorResponse> result;

        /* エラー応答の生成 */
        result = GlobalExceptionHandler.buildResponse(HttpStatus.NOT_FOUND, GlobalExceptionHandler.ERROR_NOT_FOUND,
            exception.getMessage(), request);

        return result;

    }

}
