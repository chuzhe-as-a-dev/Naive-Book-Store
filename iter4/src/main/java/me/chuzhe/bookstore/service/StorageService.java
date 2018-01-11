package me.chuzhe.bookstore.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    boolean storeAvatarAs(MultipartFile file, String filename);

    Resource loadAvatarAsResource(String filename);

    void deleteAvatarByFilename(String filename);

}
