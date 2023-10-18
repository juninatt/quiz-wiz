package se.juninatt.quizwiz;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Configuration class responsible for setting up the data source connection to the database.
 */
@Configuration
public class DataSourceConfig {

    @Autowired
    Environment env;

    /**
     * Configures and provides a DataSource bean for establishing a connection to the database.
     * The DataSource bean is configured with the driver class name, URL, username, and password
     * obtained from the application's environment properties.
     *
     * @return DataSource object configured with database connection details.
     * @throws RuntimeException    if there is an error setting up the data source.
     */
    @Bean
    public DataSource dataSource() throws DataAccessException, SQLException, IllegalStateException, BeanCreationException {
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
            throw new RuntimeException("Failed to set up the data source: " + exception.getMessage(), exception);        }
        return dataSource;
    }
}
