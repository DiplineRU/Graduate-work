package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final UserServiceImpl userService;
    private final ImageService imageService;
    private final String IMAGE_DIR = "src/main/resources/images/ads/";


    @Override
    public AdDto createAd(CreateOrUpdateAd createOrUpdateAd, MultipartFile image, String username) throws IOException {
        User user = userService.getUser(username);
        Ad ad = adMapper.toEntity(createOrUpdateAd);
        ad.setUser(user);

        if (image != null && !image.isEmpty()) {
            String fileName = imageService.saveImage(image);
            ad.setImage(fileName);
        }

        Ad savedAd = adRepository.save(ad);
        return adMapper.toDto(savedAd);
    }

    @Override
    public AdsDto getAllAds() {
        List<AdDto> adDtos = adRepository.findAll().stream()
                .map(adMapper::toDto)
                .toList();
        return new AdsDto(adDtos.size(), adDtos);
    }

    @Override
    public AdDto getAdById(Integer adId) {
        Ad ad = getAdEntity(adId);
        return adMapper.toDto(ad);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or @adService.isAuthor(authentication.name, #adId)")
    public void deleteAd(Integer adId, String username) {
        // Получаем объявление по ID
        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new RuntimeException("Ad not found with id: " + adId));

        // Проверяем права доступа
        if (!ad.getUser().getEmail().equals(username)) {
            throw new RuntimeException("You can only delete your own ads");
        }

        // Удаляем связанное изображение
        if (ad.getImage() != null) {
            try {
                Path imagePath = Paths.get(IMAGE_DIR + ad.getImage());
                Files.deleteIfExists(imagePath);
            } catch (IOException e) {
                throw new RuntimeException("Failed to delete image file", e);
            }
        }

        // Удаляем само объявление
        adRepository.deleteById(Long.valueOf(adId));
    }

    @Override
    public AdDto updateAd(Integer adId, CreateOrUpdateAd CreateOrUpdateAd, String username) {
        Ad ad = getAdEntity(adId);
        validateAdOwnership(ad, username);

        adMapper.updateAdFromDto(CreateOrUpdateAd, ad);
        Ad updatedAd = adRepository.save(ad);
        return adMapper.toDto(updatedAd);
    }

    @Override
    public List<AdDto> getAdsByUser(String username) {
        User user = userService.getUser(username);
        return adRepository.findByUser(user).stream()
                .map(adMapper::toDto)
                .toList();
    }

    @Override
    public Ad getAdEntity(Integer adId) {
        return adRepository.findById(Long.valueOf(adId))
                .orElseThrow(() -> new RuntimeException("Ad not found"));
    }


    private void validateAdOwnership(Ad ad, String username) {
        if (!ad.getUser().getEmail().equals(username)) {
            throw new RuntimeException("You can only modify your own ads");
        }
    }
    public boolean isAuthor(String username, Integer adId) {
        Ad ad = adRepository.findById(adId).orElseThrow();
        return ad.getUser().getEmail().equals(username);
    }
}
