package com.example.dashboard.start.notification;

import com.example.dashboard.start.StartApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationService implements CommandLineRunner {

    @Autowired
    NotificationServiceInterface notificationServiceObj;
//
//    public NotificationService(NotificationService notificationService) {
//        this.notificationService = notificationService;
//    }

    public static void main(String[] args) {
        SpringApplication.run(NotificationService.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("Starting Notification Service");
        notificationServiceObj.sendNotification();
    }
}
