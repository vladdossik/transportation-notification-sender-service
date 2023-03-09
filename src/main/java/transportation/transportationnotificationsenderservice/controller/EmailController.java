package transportation.transportationnotificationsenderservice.controller;


import lombok.RequiredArgsConstructor;
import notifications.email.EmailNotificationWithUserDto;
import notifications.email.EmailNotificationWithoutUserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import transportation.transportationnotificationsenderservice.mapper.NotificationConverter;

import java.util.List;

@RestController
@RequestMapping(value = "/send")
@RequiredArgsConstructor
public class EmailController {

    private final WebClient notificationClient;

    private final NotificationConverter notificationConverter;

    @PostMapping("/email")
    public Flux<EmailNotificationWithoutUserDto> sendEmailToUnknownUsers(@RequestBody List<EmailNotificationWithoutUserDto> emailNotificationWithoutUserDtos) {
        return notificationClient.post()
            .uri(uriBuilder -> uriBuilder
                .path("/notify/email")
                .build())
            .accept(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(notificationConverter.emailNotificationWithoutUserToNotification(emailNotificationWithoutUserDtos)))
            .retrieve()
            .bodyToFlux(EmailNotificationWithoutUserDto.class);
    }

    @PostMapping("/email/user")
    public Flux<EmailNotificationWithUserDto> sendEmailToKnownUsers(@RequestBody List<EmailNotificationWithUserDto> emailNotificationWithUserDtos) {
        return notificationClient.post()
            .uri(uriBuilder -> uriBuilder
                .path("/notify/email")
                .build())
            .accept(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(notificationConverter.emailNotificationWithUserToNotification(emailNotificationWithUserDtos)))
            .retrieve()
            .bodyToFlux(EmailNotificationWithUserDto.class);
    }
}
