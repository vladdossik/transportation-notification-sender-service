package transportation.transportationnotificationsenderservice.mapper;

import notifications.NotificationDto;
import notifications.NotificationSettings;
import notifications.NotificationType;
import org.springframework.stereotype.Service;
import transportation.transportationnotificationsenderservice.model.email.EmailNotificationWithUserDto;
import transportation.transportationnotificationsenderservice.model.email.EmailNotificationWithoutUserDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationConverter {
    public List<NotificationDto> emailNotificationWithoutUserToNotification(List<EmailNotificationWithoutUserDto> emailNotificationWithoutUserDtoList) {
        List<NotificationDto> notificationDtoList = new ArrayList<>();
        for (EmailNotificationWithoutUserDto emailNotificationWithoutUserDto : emailNotificationWithoutUserDtoList) {
            NotificationDto notificationDto = new NotificationDto();
            NotificationSettings notificationSettings = new NotificationSettings();
            notificationSettings.setTo(emailNotificationWithoutUserDto.getTo());
            notificationSettings.setCc(emailNotificationWithoutUserDto.getCc());
            notificationSettings.setBcc(emailNotificationWithoutUserDto.getBcc());
            notificationSettings.setSubject(emailNotificationWithoutUserDto.getSubject());
            notificationSettings.setMessage(emailNotificationWithoutUserDto.getContent());
            notificationDto.setSettings(notificationSettings);
            notificationDto.setType(NotificationType.EMAIL);
            notificationDtoList.add(notificationDto);
        }
        return notificationDtoList;
    }

    public List<NotificationDto> emailNotificationWithUserToNotification(List<EmailNotificationWithUserDto> emailNotificationWithUserDto) {
        List<NotificationDto> notificationDtoList = new ArrayList<>();
        for (EmailNotificationWithUserDto emailNotificationWithoutUserDto : emailNotificationWithUserDto) {
            NotificationDto notificationDto = new NotificationDto();
            NotificationSettings notificationSettings = new NotificationSettings();
            notificationSettings.setSubject(emailNotificationWithoutUserDto.getSubject());
            notificationSettings.setMessage(emailNotificationWithoutUserDto.getContent());
            notificationDto.setUserId(emailNotificationWithoutUserDto.getUser_id());
            notificationDto.setSettings(notificationSettings);
            notificationDto.setType(NotificationType.EMAIL);
            notificationDtoList.add(notificationDto);
        }
        return notificationDtoList;
    }
}
