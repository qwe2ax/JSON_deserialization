package org.example;

import org.example.entities.User;
import org.hibernate.Session;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
//@EntityScan("org.example.entities")
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
//        SpringApplication.run(Main.class, args);
        System.out.println("Открытие сессии");
        Session session = HibernateUtil.getSessionFactory().openSession();
        System.out.println("Начало транзакции");
        session.beginTransaction();

        System.out.println("Создается юзер");
        User user = User.builder()
                .name("Sabaka")
                .email("sabakadikaya228@gmail.com")
                .password("pass")
                .dateOfBirth(LocalDate.of(1999, Calendar.FEBRUARY, 21))
                .dateOfRegistration(LocalDateTime.now())
                .build();
        System.out.println("Сохраняется юзер");
        session.persist(user);

        System.out.println("Коммитится транзакция, че это ваще такое блять");
        session.getTransaction().commit();

        System.out.println("Закрытие сессии");
        session.close();
        System.out.println("Завершение работы Hibernate");
        HibernateUtil.shutdown();
    }
}
