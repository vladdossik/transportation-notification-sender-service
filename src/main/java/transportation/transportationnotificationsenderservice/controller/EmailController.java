package transportation.transportationnotificationsenderservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import notifications.NotificationDto;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import transportation.transportationnotificationsenderservice.mapper.NotificationConverter;
import transportation.transportationnotificationsenderservice.model.email.EmailNotificationWithUserDto;
import transportation.transportationnotificationsenderservice.model.email.EmailNotificationWithoutUserDto;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping(value = "v1/send")
@RequiredArgsConstructor
public class EmailController {

    private final WebClient notificationClient;

    private final NotificationConverter notificationConverter;

    @Operation(summary = "Отправить уведомление по email")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Запрос выполнен успешно", content = @Content(schema = @Schema(implementation = NotificationDto[].class))),
        @ApiResponse(responseCode = "400", description = "Ошибочный запрос"),
        @ApiResponse(responseCode = "503", description = "Сервис временно недоступен")
    })
    @PostMapping("/email")
    public Flux<NotificationDto> sendEmailToUnknownUsers(@RequestBody List<@Valid EmailNotificationWithoutUserDto> emailNotificationWithoutUserDtos) {
        return notificationClient.post()
            .uri(uriBuilder -> uriBuilder
                .path("/notify/email")
                .build())
            .accept(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(notificationConverter.emailNotificationWithoutUserToNotification(emailNotificationWithoutUserDtos)))
            .retrieve()
            .bodyToFlux(NotificationDto.class);
    }

    @Operation(summary = "Отправить уведомление по user_id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Запрос выполнен успешно", content = @Content(schema = @Schema(implementation = NotificationDto[].class))),
        @ApiResponse(responseCode = "400", description = "Ошибочный запрос"),
        @ApiResponse(responseCode = "503", description = "Сервис временно недоступен")
    })
    @PostMapping("/email/user")
    public Flux<NotificationDto> sendEmailToKnownUsers(@RequestBody List<@Valid EmailNotificationWithUserDto> emailNotificationWithUserDtos) {
        return notificationClient.post()
            .uri(uriBuilder -> uriBuilder
                .path("/notify/email")
                .build())
            .accept(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(notificationConverter.emailNotificationWithUserToNotification(emailNotificationWithUserDtos)))
            .retrieve()
            .bodyToFlux(NotificationDto.class);
    }
}
