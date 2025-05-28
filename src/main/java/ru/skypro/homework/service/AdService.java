package ru.skypro.homework.service;

import ru.skypro.homework.dto.AdDto;

import java.util.List;

public interface AdService {
    AdDto createAd(AdDto adDto, Long userId);
    AdDto updateAd(Long adId, AdDto adDto);
    void deleteAd(Long adId);
    List<AdDto> getAllAds();
    AdDto getAdById(Long adId);
}
