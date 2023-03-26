package transportation.transportationnotificationsenderservice;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import transportation.transportationnotificationsenderservice.model.email.EmailNotificationWithUserDto;
import transportation.transportationnotificationsenderservice.model.email.EmailNotificationWithoutUserDto;

import java.util.UUID;

@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public abstract class AbstractInitialization {

    protected static EmailNotificationWithoutUserDto emailNotificationWithoutUserDto;
    protected static EmailNotificationWithUserDto emailNotificationWithUserDto;
    protected static UUID userExternalId = UUID.randomUUID();

    @BeforeAll
    public static void init() {
        emailNotificationWithoutUserDto = new EmailNotificationWithoutUserDto();
        emailNotificationWithoutUserDto.setTo("mr.lyu27@gmail.com");
        emailNotificationWithoutUserDto.setCc("lyu20012@mail.ru");
        emailNotificationWithoutUserDto.setBcc("");
        emailNotificationWithoutUserDto.setSubject("Some subject");
        emailNotificationWithoutUserDto.setContent("Some content. Hello world!");

        emailNotificationWithUserDto = new EmailNotificationWithUserDto();
        emailNotificationWithUserDto.setUserId(userExternalId);
        emailNotificationWithUserDto.setSubject("Some subject");
        emailNotificationWithUserDto.setContent("Some content form user. Hello world!");
    }

}
