package me.chuzhe.bookstore.service.impl;

import me.chuzhe.bookstore.config.StorageProperties;
import me.chuzhe.bookstore.service.StorageService;
import me.chuzhe.bookstore.service.exception.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by tang on 2017/6/23.
 */
@Service
public class StorageServiceImpl implements StorageService {

    private final Path avatarLocation;

    @Autowired
    public StorageServiceImpl(StorageProperties properties) {
        Path rootLocation = Paths.get(properties.getLocation());
        this.avatarLocation = rootLocation.resolve(properties.getAvatarRelativeLocation());
    }

    private boolean storeAtAs(MultipartFile file, Path path, String filename) {
        if (file.isEmpty()) {
            System.out.println(file.getOriginalFilename() + " is empty");
            return false;
        }

        try {
            Files.copy(file.getInputStream(), path.resolve(filename));
        } catch (IOException e) {
            System.out.println(e.toString());
            return false;
        }

        return true;
    }

    public boolean storeAvatarAs(MultipartFile file, String filename) {
        return storeAtAs(file, avatarLocation, filename);
    }

    private Resource loadAsResource(String filename, Path path) {
        try {
            Path file = path.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    public Resource loadAvatarAsResource(String filename) {
        return loadAsResource(filename, avatarLocation);
    }

    public void deleteAvatarByFilename(String filename) {
        FileSystemUtils.deleteRecursively(avatarLocation.resolve(filename).toFile());
    }
}
