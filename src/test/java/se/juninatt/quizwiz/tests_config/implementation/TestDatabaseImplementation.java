package se.juninatt.quizwiz.tests_config.implementation;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import se.juninatt.quizwiz.tests_config.TestDataSourceConfig;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@ComponentScan(basePackages = {
        "se.juninatt.quizwiz.service",
        "se.juninatt.quizwiz.repository",
})
@Import(TestDataSourceConfig.class)
public abstract class TestDatabaseImplementation {
}
