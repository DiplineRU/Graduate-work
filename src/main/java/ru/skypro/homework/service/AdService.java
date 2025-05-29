package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.model.Ad;

import java.io.IOException;
import java.util.List;

public interface AdService {

    AdDto createAd(CreateOrUpdateAd CreateOrUpdateAd, MultipartFile image, String username) throws IOException;

    AdsDto getAllAds();

    AdDto getAdById(Integer adId);

    void deleteAd(Integer adId, String username);

    AdDto updateAd(Integer adId, CreateOrUpdateAd CreateOrUpdateAd, String username);

    List<AdDto> getAdsByUser(String username);

    Ad getAdEntity(Integer adId);
}
