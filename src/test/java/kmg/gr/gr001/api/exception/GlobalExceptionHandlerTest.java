package kmg.gr.gr001.api.exception;

import java.util.Collections;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import kmg.core.infrastructure.model.impl.KmgReflectionModelImpl;
import kmg.gr.gr001.api.dto.ErrorResponse;

/**
 * GlobalExceptionHandler のテスト
 *
 * @author KenichiroArai
 *
 * @since 0.1.0
 *
 * @version 0.1.0
 */
@SuppressWarnings({
    "nls", "static-method",
})
public class GlobalExceptionHandlerTest {

    /**
     * デフォルトコンストラクタ
     *
     * @since 0.1.0
     */
    public GlobalExceptionHandlerTest() {

        // 処理なし
    }

    /**
     * buildResponse メソッドのテスト - 正常系:統一エラー応答が生成される場合
     *
     * @throws Exception
     *                   リフレクション実行時に例外が発生した場合
     *
     * @since 0.1.0
     */
    @Test
    public void testBuildResponse_normal() throws Exception {

        /* 期待値の定義 */
        final int    expectedStatus  = HttpStatus.BAD_REQUEST.value();
        final String expectedError   = "Bad Request";
        final String expectedMessage = "validation error";
        final String expectedPath    = "/api/build";

        /* 準備 */
        final GlobalExceptionHandler testTarget = new GlobalExceptionHandler();
        final HttpServletRequest     testRequest = Mockito.mock(HttpServletRequest.class);
        Mockito.when(testRequest.getRequestURI()).thenReturn(expectedPath);
        final KmgReflectionModelImpl testReflection = new KmgReflectionModelImpl(testTarget);

        /* テスト対象の実行 */
        @SuppressWarnings("unchecked")
        final ResponseEntity<ErrorResponse> testResult = (ResponseEntity<ErrorResponse>) testReflection
            .getMethod("buildResponse", HttpStatus.BAD_REQUEST, expectedError, expectedMessage, testRequest);

        /* 検証の準備 */
        final ErrorResponse actualBody = testResult.getBody();

        /* 検証の実施 */
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, testResult.getStatusCode(), "HTTP ステータスが一致しません");
        Assertions.assertEquals(expectedStatus, actualBody.status(), "ボディのステータスが一致しません");
        Assertions.assertEquals(expectedError, actualBody.error(), "エラー種別が一致しません");
        Assertions.assertEquals(expectedMessage, actualBody.message(), "エラーメッセージが一致しません");
        Assertions.assertEquals(expectedPath, actualBody.path(), "リクエストパスが一致しません");
        Assertions.assertNotNull(actualBody.timestamp(), "発生時刻が設定されていません");

    }

    /**
     * handleException メソッドのテスト - 異常系:想定外例外の場合
     *
     * @since 0.1.0
     */
    @Test
    public void testHandleException_errorUnexpected() {

        /* 期待値の定義 */
        final int    expectedStatus  = HttpStatus.INTERNAL_SERVER_ERROR.value();
        final String expectedError   = "Internal Server Error";
        final String expectedMessage = "unexpected failure";
        final String expectedPath    = "/api/error";

        /* 準備 */
        final GlobalExceptionHandler testTarget    = new GlobalExceptionHandler();
        final Exception              testException = new RuntimeException(expectedMessage);
        final HttpServletRequest     testRequest   = Mockito.mock(HttpServletRequest.class);
        Mockito.when(testRequest.getRequestURI()).thenReturn(expectedPath);

        /* テスト対象の実行 */
        final ResponseEntity<ErrorResponse> testResult = testTarget.handleException(testException, testRequest);

        /* 検証の準備 */
        final ErrorResponse actualBody = testResult.getBody();

        /* 検証の実施 */
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, testResult.getStatusCode(), "HTTP ステータスが一致しません");
        Assertions.assertEquals(expectedStatus, actualBody.status(), "ボディのステータスが一致しません");
        Assertions.assertEquals(expectedError, actualBody.error(), "エラー種別が一致しません");
        Assertions.assertEquals(expectedMessage, actualBody.message(), "エラーメッセージが一致しません");
        Assertions.assertEquals(expectedPath, actualBody.path(), "リクエストパスが一致しません");

    }

    /**
     * handleMethodArgumentNotValid メソッドのテスト - 準正常系:フィールドエラーがある場合
     *
     * @since 0.1.0
     */
    @Test
    public void testHandleMethodArgumentNotValid_semiWithFieldError() {

        /* 期待値の定義 */
        final int    expectedStatus  = HttpStatus.BAD_REQUEST.value();
        final String expectedError   = "Bad Request";
        final String expectedMessage = "name: must not be blank";
        final String expectedPath    = "/api/validate";

        /* 準備 */
        final GlobalExceptionHandler            testTarget    = new GlobalExceptionHandler();
        final MethodArgumentNotValidException   testException = Mockito.mock(MethodArgumentNotValidException.class);
        final BindingResult                     testBinding   = Mockito.mock(BindingResult.class);
        final FieldError                        testFieldError
            = new FieldError("request", "name", "must not be blank");
        final HttpServletRequest                testRequest   = Mockito.mock(HttpServletRequest.class);
        Mockito.when(testException.getBindingResult()).thenReturn(testBinding);
        Mockito.when(testBinding.getFieldErrors()).thenReturn(List.of(testFieldError));
        Mockito.when(testRequest.getRequestURI()).thenReturn(expectedPath);

        /* テスト対象の実行 */
        final ResponseEntity<ErrorResponse> testResult
            = testTarget.handleMethodArgumentNotValid(testException, testRequest);

        /* 検証の準備 */
        final ErrorResponse actualBody = testResult.getBody();

        /* 検証の実施 */
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, testResult.getStatusCode(), "HTTP ステータスが一致しません");
        Assertions.assertEquals(expectedStatus, actualBody.status(), "ボディのステータスが一致しません");
        Assertions.assertEquals(expectedError, actualBody.error(), "エラー種別が一致しません");
        Assertions.assertEquals(expectedMessage, actualBody.message(), "エラーメッセージが一致しません");
        Assertions.assertEquals(expectedPath, actualBody.path(), "リクエストパスが一致しません");

    }

    /**
     * handleMethodArgumentNotValid メソッドのテスト - 準正常系:フィールドエラーが空の場合
     *
     * @since 0.1.0
     */
    @Test
    public void testHandleMethodArgumentNotValid_semiWithoutFieldError() {

        /* 期待値の定義 */
        final int    expectedStatus  = HttpStatus.BAD_REQUEST.value();
        final String expectedError   = "Bad Request";
        final String expectedMessage = "Validation failed";
        final String expectedPath    = "/api/validate";

        /* 準備 */
        final GlobalExceptionHandler          testTarget    = new GlobalExceptionHandler();
        final MethodArgumentNotValidException testException = Mockito.mock(MethodArgumentNotValidException.class);
        final BindingResult                   testBinding   = Mockito.mock(BindingResult.class);
        final HttpServletRequest              testRequest   = Mockito.mock(HttpServletRequest.class);
        Mockito.when(testException.getBindingResult()).thenReturn(testBinding);
        Mockito.when(testBinding.getFieldErrors()).thenReturn(Collections.emptyList());
        Mockito.when(testRequest.getRequestURI()).thenReturn(expectedPath);

        /* テスト対象の実行 */
        final ResponseEntity<ErrorResponse> testResult
            = testTarget.handleMethodArgumentNotValid(testException, testRequest);

        /* 検証の準備 */
        final ErrorResponse actualBody = testResult.getBody();

        /* 検証の実施 */
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, testResult.getStatusCode(), "HTTP ステータスが一致しません");
        Assertions.assertEquals(expectedStatus, actualBody.status(), "ボディのステータスが一致しません");
        Assertions.assertEquals(expectedError, actualBody.error(), "エラー種別が一致しません");
        Assertions.assertEquals(expectedMessage, actualBody.message(), "エラーメッセージが一致しません");
        Assertions.assertEquals(expectedPath, actualBody.path(), "リクエストパスが一致しません");

    }

    /**
     * handleNoResourceFound メソッドのテスト - 準正常系:リソースが見つからない場合
     *
     * @since 0.1.0
     */
    @Test
    public void testHandleNoResourceFound_semiNotFound() {

        /* 期待値の定義 */
        final int    expectedStatus = HttpStatus.NOT_FOUND.value();
        final String expectedError  = "Not Found";
        final String expectedPath   = "/api/missing";

        /* 準備 */
        final GlobalExceptionHandler  testTarget    = new GlobalExceptionHandler();
        final NoResourceFoundException testException
            = new NoResourceFoundException(HttpMethod.GET, "api/missing", "No static resource api/missing.");
        final HttpServletRequest      testRequest   = Mockito.mock(HttpServletRequest.class);
        Mockito.when(testRequest.getRequestURI()).thenReturn(expectedPath);

        /* テスト対象の実行 */
        final ResponseEntity<ErrorResponse> testResult
            = testTarget.handleNoResourceFound(testException, testRequest);

        /* 検証の準備 */
        final ErrorResponse actualBody    = testResult.getBody();
        final String        actualMessage = actualBody.message();

        /* 検証の実施 */
        Assertions.assertEquals(HttpStatus.NOT_FOUND, testResult.getStatusCode(), "HTTP ステータスが一致しません");
        Assertions.assertEquals(expectedStatus, actualBody.status(), "ボディのステータスが一致しません");
        Assertions.assertEquals(expectedError, actualBody.error(), "エラー種別が一致しません");
        Assertions.assertEquals(testException.getMessage(), actualMessage, "エラーメッセージが一致しません");
        Assertions.assertEquals(expectedPath, actualBody.path(), "リクエストパスが一致しません");

    }

}
