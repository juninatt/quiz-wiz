package se.juninatt.quizwiz.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("WebMvcConfig:")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebMvcConfigTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static Stream<String> resourcePaths() {
        return Stream.of(
                "/static/js/menu/quiz-selection.js",
                "/static/js/menu/quiz-creation.js",
                "/static/js/menu/leaderboard.js",
                "/static/js/utils/button-handler.js",
                "/static/js/utils/popup-manager.js",
                "/static/js/utils/quiz-result.js",
                "/static/js/utils/rest-client.js",
                "/static/js/utils/timer.js",
                "/static/js/menu.js",
                "/static/js/quiz-game.js"
        );
    }

    @ParameterizedTest
    @MethodSource("resourcePaths")
    @DisplayName("Static resources are served with 'no-cache' header")
    public void shouldServeStaticResources_WithNoCacheHeader(String resourcePath) {
        ResponseEntity<String> response = restTemplate.getForEntity(resourcePath, String.class);

        assertTrue(Objects.requireNonNull(response.getHeaders().getCacheControl()).contains("no-cache"),
                "Resource at " + resourcePath + " should have 'no-cache' header");
    }

    @ParameterizedTest
    @MethodSource("resourcePaths")
    @DisplayName("Static resources are served with 'must-revalidate' header")
    public void shouldServeStaticResources_WithMustRevalidateHeader(String resourcePath) {
        ResponseEntity<String> response = restTemplate.getForEntity(resourcePath, String.class);

        assertTrue(Objects.requireNonNull(response.getHeaders().getCacheControl()).contains("must-revalidate"),
                "Resource at " + resourcePath + " should have 'must-revalidate' header");
    }
}
