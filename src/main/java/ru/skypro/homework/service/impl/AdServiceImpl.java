package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;

import java.util.List;
@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;

    public AdServiceImpl(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public AdDto createAd(AdDto adDto, Long userId) {
        return null;
    }

    @Override
    public AdDto updateAd(Long adId, AdDto adDto) {
        return null;
    }

    @Override
    public void deleteAd(Long adId) {

    }

    @Override
    public List<AdDto> getAllAds() {
        return null;
    }

    @Override
    public AdDto getAdById(Long adId) {
        return null;
    }
}
