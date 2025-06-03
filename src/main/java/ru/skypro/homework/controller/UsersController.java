package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;
import java.io.IOException;
/**
 * Контроллер для работы с пользователями.
 * <p>
 * Обрабатывает операции:
 * <ul>
 *   <li>Обновление информации о пользователе</li>
 *   <li>Обновление аватара пользователя</li>
 *   <li>Получение информации о текущем пользователе</li>
 *   <li>Обновление пароля</li>
 * </ul>
 */
@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
public class UsersController {
    /**
     * Обновляет информацию о текущем пользователе.
     *
     * @param newPassword новые данные пользователя
     * @return обновленные данные пользователя
     */
    @Operation(
            tags = "Пользователи",
            summary = "Обновление пароля",
            operationId = "setPassword",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = NewPassword.class
                            )
                    )
            ),
            responses = {@ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            }

    )
    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody NewPassword newPassword) {
        return ResponseEntity.ok().build();
    }

    @Operation(
            tags = "Пользователи",
            summary = "Получение информации об авторизованном пользователе",
            operationId = "getUser",
            responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = UserDto.class
                    )
            )),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }

    )
    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser() {
        return ResponseEntity.ok(new UserDto());
    }

    @Operation(
            tags = "Пользователи",
            summary = "Обновление информации об авторизованном пользователе",
            operationId = "updateUser",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = UpdateUser.class
                            )
                    )
            ),
            responses = {@ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = UpdateUser.class
                            )
                    )),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }

    )
    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser) {
        return ResponseEntity.ok(updateUser);
    }
    @Operation(
            tags = "Пользователи",
            summary = "Обновление аватара авторизованного пользователя",
            operationId = "updateUserImage",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE
                    )
            ),
            responses = {@ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }

    )
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUserImage(@RequestPart MultipartFile image) throws IOException {
        byte[] file = image.getBytes();
        return ResponseEntity.ok().build();
    }

}
