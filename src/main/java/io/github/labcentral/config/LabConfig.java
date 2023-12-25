package io.github.labcentral.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.nio.file.Paths;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">zemise</a>
 * @Date 2023/12/14
 * @since 1.0
 */
@Configuration
public class LabConfig {

    @Value("${spring.database.type}")
    String database;

    @Bean
    @Primary
    @ConditionalOnProperty(name = "spring.database.type", havingValue = "mysql")
    public DataSource mysqlDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/your_database_name");
        dataSourceBuilder.username("your_mysql_username");
        dataSourceBuilder.password("your_mysql_password");
        return dataSourceBuilder.build();
    }

    @Bean
    @Primary
    @ConditionalOnProperty(name = "spring.database.type", havingValue = "h2")
    public DataSource h2DataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        // the content of the database is lost at the moment the last connection is closed.
        // f you want to keep your content you have to configure the url like this
        // ;DB_CLOSE_DELAY=-1
        dataSource.setUrl("jdbc:h2:mem:labdb;DB_CLOSE_DELAY=-1");
        //dataSource.setUrl("jdbc:h2:mem:labdb;DATABASE_TO_UPPER=false;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    @Primary
    @ConditionalOnProperty(name = "spring.database.type", havingValue = "sqlite")
    public DataSource sqliteDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");

        String dbPath = Paths.get(System.getProperty("user.home"), "sqlite.db").toString();

        //File dbFile = new File(dbPath);
        //if (!dbFile.exists()) {
        //    dbFile.mkdir();
        //}
        String dbUrl = String.format("jdbc:sqlite:%s", dbPath);
        //dataSource.setUrl("jdbc:sqlite:/Users/zemise/Downloads/sqlite.db");
        dataSource.setUrl(dbUrl);
        return dataSource;
    }

}
