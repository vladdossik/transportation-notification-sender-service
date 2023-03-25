package transportation.transportationnotificationsenderservice.model.email;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class EmailNotificationWithUserDto {
    @NotNull(message = "Field \"user_id\" is mandatory")
    @Schema(description = "UUID пользователя")
    private UUID user_id;

    @Schema(description = "Тема письма")
    private String subject;

    @NotBlank(message = "Field \"content\" is mandatory")
    @NotNull
    @Schema(description = "Сообщение")
    private String content;

//    @Schema(description = "Приложенный файл")
//    private File attachment;
}

