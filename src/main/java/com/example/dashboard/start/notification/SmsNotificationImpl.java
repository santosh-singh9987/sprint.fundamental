package com.example.dashboard.start.notification;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("sms")
@ConditionalOnProperty(name = "notification.type", havingValue = "sms")
public class SmsNotificationImpl implements NotificationServiceInterface {

    @Override
    public boolean sendNotification() {
        System.out.println("SMS Notification");
        return false;
    }
}
