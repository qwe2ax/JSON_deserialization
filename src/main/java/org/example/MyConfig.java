package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.dao.UserDAO;
import org.example.dao.UserDAOImpl;
import org.example.entities.CloseInfoItem;
import org.example.entities.Company;
import org.example.entities.Entrepreneur;
import org.example.services.DataService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;

@Configuration
@ComponentScan("org.example")
public class MyConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/testdb");
        dataSource.setUsername("postgres");
        dataSource.setPassword("root91");
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("org.example.entities");
        Properties hibernateProperties = new Properties();
        hibernateProperties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        hibernateProperties.put(Environment.SHOW_SQL, "true");
        hibernateProperties.put(Environment.FORMAT_SQL, "true");
        hibernateProperties.put(Environment.HBM2DDL_AUTO, "update");

        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }

    @Bean
    public UserDAO userDAO(SessionFactory sessionFactory) {
        return new UserDAOImpl(sessionFactory);
    }

    @Bean
    public List<Company> companies(DataService dataService) {
        return dataService.getData("companies.json", new TypeReference<>() {});
    }

    @Bean
    public List<Entrepreneur> entrepreneurs(DataService dataService) {
        return dataService.getData("entrepreneurs.json", new TypeReference<>() {});
    }

    @Bean
    public List<CloseInfoItem> closeInfoItems(DataService dataService) {
        return dataService.getData("closeinfo.json", new TypeReference<>() {});
    }
}
