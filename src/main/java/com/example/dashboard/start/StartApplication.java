package com.example.dashboard.start;

import com.example.dashboard.start.notification.NotificationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootApplication
public class StartApplication implements CommandLineRunner {

//    @Autowired
//    PaymentService paymentService;

//    NotificationServiceInterface notificationServiceInterfaceObj;
//    public StartApplication (@Qualifier("sms") NotificationServiceInterface notificationServiceInterfaceObj){
//        this.notificationServiceInterfaceObj = notificationServiceInterfaceObj;
//    }

    @Autowired
    Map<String, NotificationServiceInterface> notificationServiceMap = new LinkedHashMap<>();

//    public StartApplication (Map<String, NotificationServiceInterface> notificationServiceMap){
//        this.notificationServiceMap = notificationServiceMap;
//    }

//    iterate all the notification servcie


    public static void main(String[] args) {
//        System.out.println("Hello World!");
//        paymentService.pay();
		SpringApplication.run(StartApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
//        notificationInterfaceService notificationService = new EmailNotificationInterface();
//        notificationServiceInterfaceObj.sendNotification();
        for (var NotificationServiceInterface : notificationServiceMap.entrySet()) {
            System.out.println(NotificationServiceInterface.getKey());
            System.out.println(NotificationServiceInterface.getValue().sendNotification());
        }
    }
}
