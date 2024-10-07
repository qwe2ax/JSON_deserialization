package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//@EntityScan("org.example.entities")
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
//        System.out.println("Открытие сессии");
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        System.out.println("Начало транзакции");
//        session.beginTransaction();
//
//        System.out.println("Создается юзер");
//        User user = User.builder()
//                .name("Sabaka1")
//                .email("sabakadikaya128@gmail.com")
//                .password("pass123")
//                .dateOfBirth(LocalDate.of(1989, Calendar.FEBRUARY, 21))
//                .build();
//        System.out.println("Сохраняется юзер");
//        session.persist(user);
//
//        System.out.println("Коммитится транзакция, че это ваще такое блять");
//        session.getTransaction().commit();
//
//        System.out.println("Закрытие сессии");
//        session.close();
//        System.out.println("Завершение работы Hibernate");
//        HibernateUtil.shutdown();
    }
}
