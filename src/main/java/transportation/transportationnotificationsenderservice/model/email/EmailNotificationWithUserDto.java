package transportation.transportationnotificationsenderservice.model.email;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class EmailNotificationWithUserDto {
    @NotNull(message = "Field \"user_id\" is mandatory")
    @Schema(description = "UUID пользователя")
    private UUID user_id;

    @NotBlank(message = "Field \"subject\" is mandatory")
    @NotNull
    @Schema(description = "Тема письма")
    private String subject;

    @NotBlank(message = "Field \"content\" is mandatory")
    @NotNull
    @Schema(description = "Сообщение")
    private String content;

//    @Schema(description = "Приложенный файл")
//    private File attachment;
}

