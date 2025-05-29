package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final UserServiceImpl userService;
    private final String IMAGE_DIR = "src/main/resources/images/ads/";

    @Override
    public AdDto createAd(CreateOrUpdateAd CreateOrUpdateAd, MultipartFile image, String username) throws IOException {
        User user = userService.getUser(username);

        Ad ad = adMapper.toEntity(CreateOrUpdateAd);
        ad.setUser(user);

        if (image != null && !image.isEmpty()) {
            String fileName = saveImage(image);
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
    public void deleteAd(Integer adId, String username) {
        Ad ad = getAdEntity(adId);
        validateAdOwnership(ad, username);

        if (ad.getImage() != null) {
            deleteImage(ad.getImage());
        }

        adRepository.delete(ad);
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

    private String saveImage(MultipartFile image) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        Path path = Paths.get(IMAGE_DIR + fileName);
        Files.createDirectories(path.getParent());
        Files.write(path, image.getBytes());
        return fileName;
    }

    private void deleteImage(String imageName) {
        try {
            Path path = Paths.get(IMAGE_DIR + imageName);
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete image", e);
        }
    }

    private void validateAdOwnership(Ad ad, String username) {
        if (!ad.getUser().getEmail().equals(username)) {
            throw new RuntimeException("You can only modify your own ads");
        }
    }
}
