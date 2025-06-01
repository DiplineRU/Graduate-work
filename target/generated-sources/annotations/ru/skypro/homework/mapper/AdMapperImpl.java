package ru.skypro.homework.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-01T23:26:23+0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.14 (Amazon.com Inc.)"
)
@Component
public class AdMapperImpl implements AdMapper {

    @Override
    public AdEntity toAd(AdDto adDTO) {
        if ( adDTO == null ) {
            return null;
        }

        AdEntity adEntity = new AdEntity();

        if ( adDTO.getPk() != null ) {
            adEntity.setId( adDTO.getPk().intValue() );
        }
        adEntity.setPrice( adDTO.getPrice() );
        adEntity.setTitle( adDTO.getTitle() );

        return adEntity;
    }

    @Override
    public AdDto toAdDto(AdEntity ad) {
        if ( ad == null ) {
            return null;
        }

        AdDto adDto = new AdDto();

        adDto.setAuthor( adAuthorId( ad ) );
        if ( ad.getId() != null ) {
            adDto.setPk( ad.getId().longValue() );
        }
        adDto.setPrice( ad.getPrice() );
        adDto.setTitle( ad.getTitle() );

        adDto.setImage( buildImageUrl(ad.getId()) );

        return adDto;
    }

    @Override
    public List<AdDto> toListAdDTO(List<AdEntity> ads) {
        if ( ads == null ) {
            return null;
        }

        List<AdDto> list = new ArrayList<AdDto>( ads.size() );
        for ( AdEntity adEntity : ads ) {
            list.add( toAdDto( adEntity ) );
        }

        return list;
    }

    @Override
    public ExtendedAdDto adToExtendedAd(AdEntity ad) {
        if ( ad == null ) {
            return null;
        }

        ExtendedAdDto extendedAdDto = new ExtendedAdDto();

        if ( ad.getId() != null ) {
            extendedAdDto.setPk( ad.getId().longValue() );
        }
        extendedAdDto.setAuthorFirstName( adAuthorFirstName( ad ) );
        extendedAdDto.setAuthorLastName( adAuthorLastName( ad ) );
        extendedAdDto.setPhone( adAuthorPhone( ad ) );
        extendedAdDto.setEmail( adAuthorEmail( ad ) );
        extendedAdDto.setDescription( ad.getDescription() );
        if ( ad.getPrice() != null ) {
            extendedAdDto.setPrice( ad.getPrice() );
        }
        extendedAdDto.setTitle( ad.getTitle() );

        extendedAdDto.setImage( buildImageUrl(ad.getId()) );

        return extendedAdDto;
    }

    private Long adAuthorId(AdEntity adEntity) {
        if ( adEntity == null ) {
            return null;
        }
        UserEntity author = adEntity.getAuthor();
        if ( author == null ) {
            return null;
        }
        Long id = author.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String adAuthorFirstName(AdEntity adEntity) {
        if ( adEntity == null ) {
            return null;
        }
        UserEntity author = adEntity.getAuthor();
        if ( author == null ) {
            return null;
        }
        String firstName = author.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private String adAuthorLastName(AdEntity adEntity) {
        if ( adEntity == null ) {
            return null;
        }
        UserEntity author = adEntity.getAuthor();
        if ( author == null ) {
            return null;
        }
        String lastName = author.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }

    private String adAuthorPhone(AdEntity adEntity) {
        if ( adEntity == null ) {
            return null;
        }
        UserEntity author = adEntity.getAuthor();
        if ( author == null ) {
            return null;
        }
        String phone = author.getPhone();
        if ( phone == null ) {
            return null;
        }
        return phone;
    }

    private String adAuthorEmail(AdEntity adEntity) {
        if ( adEntity == null ) {
            return null;
        }
        UserEntity author = adEntity.getAuthor();
        if ( author == null ) {
            return null;
        }
        String email = author.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }
}
