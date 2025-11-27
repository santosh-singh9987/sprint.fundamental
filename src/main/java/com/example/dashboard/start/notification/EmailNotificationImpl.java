package com.example.dashboard.start.notification;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
//@Qualifier("email")
@ConditionalOnProperty(name = "notification.type", havingValue = "email")
public class EmailNotificationImpl implements NotificationServiceInterface {

    @Override
    public boolean sendNotification() {
        System.out.println("Email Notification");
        return false;
    }
}
