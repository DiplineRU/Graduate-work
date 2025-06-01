package ru.skypro.homework.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;



public class ImageUpdateDto {

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
