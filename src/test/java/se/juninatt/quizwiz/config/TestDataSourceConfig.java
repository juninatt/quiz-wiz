package se.juninatt.quizwiz.config;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * Configuration class for setting up the test data source.
 * This configuration is active when the "test" profile is active.
 */
@Configuration
@Profile("test")
public class TestDataSourceConfig {

    @Autowired
    private Environment env;

    /**
     * Configures and provides a DataSource bean for establishing a connection to the test database.
     * The DataSource bean is configured with the information obtained from the application's test properties file.
     *
     * @return DataSource object configured with database connection details.
     * @throws RuntimeException    If there is an error setting up the data source.
     */
    @Bean
    public DataSource dataSourceTests() throws DataAccessException, IllegalStateException, BeanCreationException {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        try {
            dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("driverClassName"),
                    "Database driver class name must be provided in the application properties."));
            dataSource.setUrl(Objects.requireNonNull(env.getProperty("url"),
                    "Database URL must be provided in the application properties."));
            dataSource.setUsername(Objects.requireNonNull(env.getProperty("username"),
                    "Database username must be provided in the application properties."));
            dataSource.setPassword(Objects.requireNonNull(env.getProperty("password"),
                    "Database password must be provided in the application properties."));
        } catch (DataAccessException | IllegalStateException | BeanCreationException exception) {
            throw new RuntimeException("Failed to set up the data source: " + exception.getMessage(), exception);
        }
        return dataSource;
    }
}
