package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
@RequiredArgsConstructor

public class AdsController {

    private final AdService adService;
    private final CommentService commentService;


    @Operation(
            tags = "Объявления",
            summary = "Получение всех объявлений",
            operationId = "getAllAds",
            responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = AdsDto.class
                    )
            ))
            }

    )
    @GetMapping
    public ResponseEntity<?> getAllAds() {
        return ResponseEntity.ok().build();
    }

    @Operation(
            tags = "Объявления",
            summary = "Добавление объявления",
            operationId = "addAd",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = {@Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
                    }
            ),
            responses = {@ApiResponse(responseCode = "200", description = "OK" , content = @Content(
                    schema = @Schema(
                            implementation = AdDto.class
                    )
            )),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }

    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDto> addAd(
            @RequestPart CreateOrUpdateAd properties,
            @RequestPart MultipartFile image,
            Principal principal) throws IOException {

        AdDto createdAd = adService.createAd(properties, image, principal.getName());
        return ResponseEntity.ok(createdAd);
    }

    @Operation(
            tags = "Объявления",
            summary = "Получение информации об объявлении",
            operationId = "getAds",
            parameters = @Parameter(
                    name = "id",
                    in = ParameterIn.PATH,
                    required = true,
                    schema = @Schema(
                            type = "integer",
                            format = "int32"
                    )
            ),
            responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = ExtendedAd.class
                    )
            )),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getAds(@PathVariable int id) {
        return ResponseEntity.ok().build();
    }

    @Operation(
            tags = "Объявления",
            summary = "Удаление объявления",
            operationId = "removeAd",
            parameters = @Parameter(
                    name = "id",
                    in = ParameterIn.PATH,
                    required = true,
                    schema = @Schema(
                            type = "integer",
                            format = "int32"
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAd(@PathVariable int id) {
        return ResponseEntity.ok().build();
    }

    @Operation(
            tags = "Объявления",
            summary = "Обновление информации об объявлении",
            operationId = "updateAds",
            parameters = @Parameter(
                    name = "id",
                    in = ParameterIn.PATH,
                    required = true,
                    schema = @Schema(
                            type = "integer",
                            format = "int32"
                    )
            ),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = CreateOrUpdateAd.class
                            )
                    )
                    }
            ),
            responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = AdDto.class
                    )
            )),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateAds(@PathVariable int id, @RequestBody CreateOrUpdateAd createOrUpdateAd) {
        return ResponseEntity.ok().build();
    }

    @Operation(
            tags = "Объявления",
            summary = "Получение объявлений авторизованного пользователя",
            operationId = "getAdsMe",
            responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = AdsDto.class
                    )
            )),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @GetMapping("/me")
    public ResponseEntity<?> getAdsMe() {
        return ResponseEntity.ok().build();
    }

    @Operation(
            tags = "Объявления",
            summary = "Обновление картинки объявления",
            operationId = "updateImage",
            parameters = @Parameter(
                    name = "id",
                    in = ParameterIn.PATH,
                    required = true,
                    schema = @Schema(
                            type = "integer",
                            format = "int32"
                    )
            ),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = {@Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE
                    )
                    }
            ),
            responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE,
                    array = @ArraySchema(
                            schema = @Schema(
                                    type = "string",
                                    format = "byte"
                            )
                    )

            )),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @PatchMapping(value = "/{id}/image" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateImage(@PathVariable int id, @RequestBody MultipartFile image) {
        return ResponseEntity.ok().build();
    }

    @Operation(
            tags = "Комментарии",
            summary = "Получение комментариев объявления",
            operationId = "getComments",
            parameters = @Parameter(
                    name = "id",
                    in = ParameterIn.PATH,
                    required = true,
                    schema = @Schema(
                            type = "integer",
                            format = "int32"
                    )
            ),
            responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = Comments.class
                    )

            )),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @GetMapping("/{id}/comments")
    public ResponseEntity<?> getComments(@PathVariable int id) {
        return ResponseEntity.ok().build();
    }

    @Operation(
            tags = "Комментарии",
            summary = "Добавление комментария к объявлению",
            operationId = "addComment",
            parameters = @Parameter(
                    name = "id",
                    in = ParameterIn.PATH,
                    required = true,
                    schema = @Schema(
                            type = "integer",
                            format = "int32"
                    )
            ),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = CreateOrUpdateComment.class
                            )
                    )
                    }
            ),
            responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = Comments.class
                    )
            )),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @PostMapping("/{id}/comments")
    public ResponseEntity<?> addComment(@PathVariable int id) {
        return ResponseEntity.ok().build();
    }

    @Operation(
            tags = "Комментарии",
            summary = "Удаление комментария",
            operationId = "deleteComment",
            parameters = {
                    @Parameter(
                            name = "adId",
                            in = ParameterIn.PATH,
                            required = true,
                            schema = @Schema(
                                    type = "integer",
                                    format = "int32"
                            )
                    ),
                    @Parameter(
                            name = "commentId",
                            in = ParameterIn.PATH,
                            required = true,
                            schema = @Schema(
                                    type = "integer",
                                    format = "int32"
                            )
                    )
            },
            responses = {@ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int adId, @PathVariable int commentId) {
        return ResponseEntity.ok().build();
    }


    @Operation(
            tags = "Комментарии",
            summary = "Обновление комментария",
            operationId = "updateComment",
            parameters = {
                    @Parameter(
                            name = "adId",
                            in = ParameterIn.PATH,
                            required = true,
                            schema = @Schema(
                                    type = "integer",
                                    format = "int32"
                            )
                    ),
                    @Parameter(
                            name = "commentId",
                            in = ParameterIn.PATH,
                            required = true,
                            schema = @Schema(
                                    type = "integer",
                                    format = "int32"
                            )
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = CreateOrUpdateComment.class
                            )
                    )
            ),
            responses = {@ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = CommentDto.class
                            )
                    )),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable int adId,
                                           @PathVariable int commentId,
                                           @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        return ResponseEntity.ok().build();
    }
}
