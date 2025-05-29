package ru.skypro.homework.mapper;

import org.mapstruct.*;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.model.Ad;

@Mapper(componentModel = "spring")
public interface AdMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "image", expression = "java(createAdDto.getImage() != null ? \"ads/\" + System.currentTimeMillis() + \"_\" + createAdDto.getImage().getOriginalFilename() : null)")
    Ad toEntity(CreateOrUpdateAd createAdDto);

    @Mapping(target = "author", source = "user.id")
    @Mapping(target = "authorFirstName", source = "user.firstName")
    @Mapping(target = "authorLastName", source = "user.lastName")
    @Mapping(target = "phone", source = "user.phone")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "image", expression = "java(\"/images/\" + ad.getImage())")
    AdDto toDto(Ad ad);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "image", ignore = true)
    void updateAdFromDto(CreateOrUpdateAd dto, @MappingTarget Ad ad);

}
