package transportation.transportationnotificationsenderservice.mapper;

import notifications.NotificationDto;
import notifications.NotificationType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import transportation.transportationnotificationsenderservice.AbstractInitialization;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class NotificationConverterTest extends AbstractInitialization {
    @InjectMocks
    NotificationConverter notificationConverter;

    @Test
    public void emailNotificationWithoutUserToNotificationTest() {
        List<NotificationDto> notificationDtoList = notificationConverter.emailNotificationWithoutUserToNotification(List.of(emailNotificationWithoutUserDto));
        NotificationDto notificationDto = notificationDtoList.get(0);
        assertNull(notificationDto.getUserId());
        assertEquals(notificationDto.getType(), NotificationType.EMAIL);
        assertEquals(notificationDto.getSettings().getTo(), emailNotificationWithoutUserDto.getTo());
        assertEquals(notificationDto.getSettings().getCc(), emailNotificationWithoutUserDto.getCc());
        assertEquals(notificationDto.getSettings().getBcc(), emailNotificationWithoutUserDto.getBcc());
        assertEquals(notificationDto.getSettings().getSubject(), emailNotificationWithoutUserDto.getSubject());
        assertEquals(notificationDto.getSettings().getMessage(), emailNotificationWithoutUserDto.getContent());
    }

    @Test
    public void emailNotificationWithUserToNotificationTest() {
        List<NotificationDto> notificationDtoList = notificationConverter.emailNotificationWithUserToNotification(List.of(emailNotificationWithUserDto));
        NotificationDto notificationDto = notificationDtoList.get(0);
        assertEquals(notificationDto.getUserId(), emailNotificationWithUserDto.getUser_id());
        assertEquals(notificationDto.getType(), NotificationType.EMAIL);
        assertNull(notificationDto.getSettings().getTo());
        assertNull(notificationDto.getSettings().getCc());
        assertNull(notificationDto.getSettings().getBcc());
        assertEquals(notificationDto.getSettings().getSubject(), emailNotificationWithUserDto.getSubject());
        assertEquals(notificationDto.getSettings().getMessage(), emailNotificationWithUserDto.getContent());
    }
}
