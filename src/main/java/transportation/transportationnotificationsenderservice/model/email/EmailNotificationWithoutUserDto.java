package transportation.transportationnotificationsenderservice.model.email;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmailNotificationWithoutUserDto {
    @Email(message = "Field \"to\" should have email format")
    @NotBlank(message = "Email address \"to\" is mandatory")
    @NotNull
    @Schema(description = "Кому")
    private String to;

    @Schema(description = "Копия")
    private String cc;

    @Schema(description = "Скрытая копия")
    private String bcc;

    @Schema(description = "Тема письма")
    private String subject;

    @NotBlank(message = "Field \"content\" is mandatory")
    @NotNull
    @Schema(description = "Сообщение")
    private String content;

//    @Schema(description = "Приложенный файл")
//    private File attachment;
}