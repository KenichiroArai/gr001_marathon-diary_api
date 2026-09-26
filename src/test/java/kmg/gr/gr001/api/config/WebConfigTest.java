package kmg.gr.gr001.api.config;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import kmg.core.infrastructure.model.impl.KmgReflectionModelImpl;

/**
 * WebConfig のテスト
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
public class WebConfigTest {

    /**
     * デフォルトコンストラクタ
     *
     * @since 0.1.0
     */
    public WebConfigTest() {

        // 処理なし
    }

    /**
     * addCorsMappings メソッドのテスト - 正常系:許可オリジンが設定されている場合
     *
     * @throws Exception
     *                   リフレクション実行時に例外が発生した場合
     *
     * @since 0.1.0
     */
    @Test
    public void testAddCorsMappings_normalWithOrigins() throws Exception {

        /* 期待値の定義 */
        final String[] expectedAllowedOrigins = {
            "http://localhost:3000", "http://127.0.0.1:3000",
        };
        final String   expectedPathPattern    = "/api/**";

        /* 準備 */
        final CorsProperties testCorsProperties = new CorsProperties(List.of(expectedAllowedOrigins));
        final WebConfig      testTarget         = new WebConfig(testCorsProperties);
        final CorsRegistry   testRegistry       = new CorsRegistry();

        /* テスト対象の実行 */
        testTarget.addCorsMappings(testRegistry);

        /* 検証の準備 */
        final KmgReflectionModelImpl testReflection = new KmgReflectionModelImpl(testRegistry);
        @SuppressWarnings("unchecked")
        final List<CorsRegistration> actualRegistrations
            = (List<CorsRegistration>) testReflection.get("registrations");
        final CorsRegistration       actualRegistration  = actualRegistrations.get(0);
        final KmgReflectionModelImpl registrationReflect = new KmgReflectionModelImpl(actualRegistration);
        final String                 actualPathPattern   = (String) registrationReflect.get("pathPattern");
        final Object                 actualConfig        = registrationReflect.get("config");
        final KmgReflectionModelImpl configReflect       = new KmgReflectionModelImpl(actualConfig);
        @SuppressWarnings("unchecked")
        final List<String>           actualAllowedOrigins
            = (List<String>) configReflect.getMethod("getAllowedOrigins");

        /* 検証の実施 */
        Assertions.assertEquals(1, actualRegistrations.size(), "CORS 登録数が一致しません");
        Assertions.assertEquals(expectedPathPattern, actualPathPattern, "パスパターンが一致しません");
        Assertions.assertEquals(expectedAllowedOrigins.length, actualAllowedOrigins.size(), "許可オリジン数が一致しません");
        Assertions.assertEquals(expectedAllowedOrigins[0], actualAllowedOrigins.get(0), "1件目の許可オリジンが一致しません");
        Assertions.assertEquals(expectedAllowedOrigins[1], actualAllowedOrigins.get(1), "2件目の許可オリジンが一致しません");

    }

    /**
     * addCorsMappings メソッドのテスト - 準正常系:許可オリジンが null の場合
     *
     * @throws Exception
     *                   リフレクション実行時に例外が発生した場合
     *
     * @since 0.1.0
     */
    @Test
    public void testAddCorsMappings_semiNullOrigins() throws Exception {

        /* 期待値の定義 */
        final int expectedAllowedOriginCount = 0;

        /* 準備 */
        final CorsProperties testCorsProperties = new CorsProperties(null);
        final WebConfig      testTarget         = new WebConfig(testCorsProperties);
        final CorsRegistry   testRegistry       = new CorsRegistry();

        /* テスト対象の実行 */
        testTarget.addCorsMappings(testRegistry);

        /* 検証の準備 */
        final KmgReflectionModelImpl testReflection = new KmgReflectionModelImpl(testRegistry);
        @SuppressWarnings("unchecked")
        final List<CorsRegistration> actualRegistrations
            = (List<CorsRegistration>) testReflection.get("registrations");
        final CorsRegistration       actualRegistration  = actualRegistrations.get(0);
        final KmgReflectionModelImpl registrationReflect = new KmgReflectionModelImpl(actualRegistration);
        final Object                 actualConfig        = registrationReflect.get("config");
        final KmgReflectionModelImpl configReflect       = new KmgReflectionModelImpl(actualConfig);
        @SuppressWarnings("unchecked")
        final List<String>           actualAllowedOrigins
            = (List<String>) configReflect.getMethod("getAllowedOrigins");

        /* 検証の実施 */
        Assertions.assertEquals(1, actualRegistrations.size(), "CORS 登録数が一致しません");
        Assertions.assertNotNull(actualAllowedOrigins, "許可オリジン一覧が null です");
        Assertions.assertEquals(expectedAllowedOriginCount, actualAllowedOrigins.size(),
            "許可オリジン数が一致しません");

    }

    /**
     * コンストラクタのテスト - 正常系:CorsProperties を渡した場合
     *
     * @throws Exception
     *                   リフレクション実行時に例外が発生した場合
     *
     * @since 0.1.0
     */
    @Test
    public void testConstructor_normal() throws Exception {

        /* 期待値の定義 */
        final List<String> expectedAllowedOrigins = List.of("http://localhost:3000");

        /* 準備 */
        final CorsProperties testCorsProperties = new CorsProperties(expectedAllowedOrigins);

        /* テスト対象の実行 */
        final WebConfig testTarget = new WebConfig(testCorsProperties);

        /* 検証の準備 */
        final KmgReflectionModelImpl testReflection       = new KmgReflectionModelImpl(testTarget);
        final CorsProperties         actualCorsProperties = (CorsProperties) testReflection.get("corsProperties");

        /* 検証の実施 */
        Assertions.assertEquals(testCorsProperties, actualCorsProperties, "CORS 設定プロパティが一致しません");
        Assertions.assertEquals(expectedAllowedOrigins, actualCorsProperties.allowedOrigins(),
            "許可オリジン一覧が一致しません");

    }

}
