package org.example;


import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;

@RequiredArgsConstructor
@org.springframework.context.annotation.Configuration
public class HibernateConfig {

    private final SessionFactory sessionFactory;

    @PreDestroy
    public void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
