package kmg.marathondiary.api.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.servlet.http.HttpServletRequest;
import kmg.marathondiary.api.dto.ErrorResponse;

/**
 * API 全体の例外を統一エラー応答へ変換するハンドラ
 *
 * @author KenichiroArai
 * @since 0.1.0
 * @version 0.1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * リクエストパスが見つからない場合のハンドリング
	 *
	 * @param exception
	 *                    発生した例外
	 * @param request
	 *                    HTTP リクエスト
	 * @return 統一エラー応答
	 */
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoResourceFound(final NoResourceFoundException exception,
			final HttpServletRequest request) {

		/* 戻り値の宣言 */
		final ResponseEntity<ErrorResponse> result;

		/* エラー応答の生成 */
		result = this.buildResponse(HttpStatus.NOT_FOUND, "Not Found", exception.getMessage(), request);

		return result;

	}

	/**
	 * 入力検証エラーのハンドリング
	 *
	 * @param exception
	 *                    発生した例外
	 * @param request
	 *                    HTTP リクエスト
	 * @return 統一エラー応答
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(final MethodArgumentNotValidException exception,
			final HttpServletRequest request) {

		/* 戻り値の宣言 */
		final ResponseEntity<ErrorResponse> result;

		/* 先頭の検証メッセージを取得 */
		final String message = exception.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getField() + ": " + error.getDefaultMessage()).findFirst().orElse("Validation failed");

		/* エラー応答の生成 */
		result = this.buildResponse(HttpStatus.BAD_REQUEST, "Bad Request", message, request);

		return result;

	}

	/**
	 * 想定外例外のハンドリング
	 *
	 * @param exception
	 *                    発生した例外
	 * @param request
	 *                    HTTP リクエスト
	 * @return 統一エラー応答
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(final Exception exception, final HttpServletRequest request) {

		/* 戻り値の宣言 */
		final ResponseEntity<ErrorResponse> result;

		/* エラー応答の生成 */
		result = this.buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", exception.getMessage(),
				request);

		return result;

	}

	/**
	 * 統一エラー応答を組み立てる
	 *
	 * @param status
	 *                   HTTP ステータス
	 * @param error
	 *                   エラー種別
	 * @param message
	 *                   エラーメッセージ
	 * @param request
	 *                   HTTP リクエスト
	 * @return 統一エラー応答
	 */
	private ResponseEntity<ErrorResponse> buildResponse(final HttpStatus status, final String error,
			final String message, final HttpServletRequest request) {

		/* 戻り値の宣言 */
		final ResponseEntity<ErrorResponse> result;

		/* ボディの生成 */
		final ErrorResponse body = new ErrorResponse(Instant.now(), status.value(), error, message,
				request.getRequestURI());
		result = ResponseEntity.status(status).body(body);

		return result;

	}

}
