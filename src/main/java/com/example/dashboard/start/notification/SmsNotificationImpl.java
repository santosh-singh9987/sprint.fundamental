package com.example.dashboard.start.notification;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
@Qualifier("sms")
//@ConditionalOnProperty(name = "notification.type", havingValue = "sms")
public class SmsNotificationImpl implements NotificationServiceInterface {

    @Override
    public boolean sendNotification() {
        System.out.println("SMS Notification");
        return false;
    }
}
