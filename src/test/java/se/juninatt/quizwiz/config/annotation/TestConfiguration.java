package se.juninatt.quizwiz.config.annotation;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import se.juninatt.quizwiz.config.TestDataSourceConfig;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test")
@Import(TestDataSourceConfig.class)
public @interface TestConfiguration {
    // You can leave this empty, or you can define attributes
    // that can be set when using this composed annotation.
}
