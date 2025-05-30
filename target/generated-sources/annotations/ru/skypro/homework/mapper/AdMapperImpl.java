package ru.skypro.homework.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T12:49:12+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class AdMapperImpl implements AdMapper {

    @Override
    public Ad toEntity(CreateOrUpdateAd createAdDto) {
        if ( createAdDto == null ) {
            return null;
        }

        Ad.AdBuilder ad = Ad.builder();

        ad.image( createAdDtoImageFileOriginalFilename( createAdDto ) );
        ad.price( createAdDto.getPrice() );
        ad.title( createAdDto.getTitle() );
        ad.description( createAdDto.getDescription() );

        return ad.build();
    }

    @Override
    public AdDto toDto(Ad ad) {
        if ( ad == null ) {
            return null;
        }

        AdDto adDto = new AdDto();

        adDto.setAuthor( adUserId( ad ) );
        adDto.setAuthorFirstName( adUserFirstName( ad ) );
        adDto.setAuthorLastName( adUserLastName( ad ) );
        adDto.setPhone( adUserPhone( ad ) );
        adDto.setPrice( ad.getPrice() );
        adDto.setId( ad.getId() );
        adDto.setTitle( ad.getTitle() );

        adDto.setImage( "/images/" + ad.getImage() );

        return adDto;
    }

    @Override
    public void updateAdFromDto(CreateOrUpdateAd dto, Ad ad) {
        if ( dto == null ) {
            return;
        }

        ad.setPrice( dto.getPrice() );
        ad.setTitle( dto.getTitle() );
        ad.setDescription( dto.getDescription() );
    }

    private String createAdDtoImageFileOriginalFilename(CreateOrUpdateAd createOrUpdateAd) {
        MultipartFile imageFile = createOrUpdateAd.getImageFile();
        if ( imageFile == null ) {
            return null;
        }
        return imageFile.getOriginalFilename();
    }

    private Integer adUserId(Ad ad) {
        User user = ad.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }

    private String adUserFirstName(Ad ad) {
        User user = ad.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getFirstName();
    }

    private String adUserLastName(Ad ad) {
        User user = ad.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getLastName();
    }

    private String adUserPhone(Ad ad) {
        User user = ad.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getPhone();
    }
}
