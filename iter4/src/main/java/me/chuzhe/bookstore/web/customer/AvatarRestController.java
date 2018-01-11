package me.chuzhe.bookstore.web.customer;

/**
 * Created by tang on 2017/6/4.
 */

import me.chuzhe.bookstore.service.StorageService;
import me.chuzhe.bookstore.service.UserService;
import me.chuzhe.bookstore.service.exception.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Objects;

@RestController
@RequestMapping("/image/avatar")
public class AvatarRestController {

    private final StorageService storageService;

    private final UserService userService;

    @Autowired
    public AvatarRestController(StorageService storageService, UserService userService) {
        this.storageService = storageService;
        this.userService = userService;
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getAvatar(@PathVariable String filename) {

        Resource file = storageService.loadAvatarAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @PostMapping()
    public boolean updateAvatar(@RequestParam MultipartFile file, Principal principal) {
        if (!file.getContentType().equals("image/jpeg") && !file.getContentType().equals("image/jpg")) {
            return false;
        }


        String newFilename;
        if (file.getOriginalFilename().indexOf(".") > 0) {
            newFilename = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf("."));
        } else {
            newFilename = file.getOriginalFilename();
        }
        newFilename = newFilename + new Timestamp(System.currentTimeMillis()).getTime() + ".jpeg";

        String oldAvatarFilename = userService.getAvatarFilenameByUsername(principal.getName());
        if (!Objects.equals(oldAvatarFilename, "default.jpg")) {
            storageService.deleteAvatarByFilename(oldAvatarFilename);
        }

        return userService.updateAvatarFilename(newFilename, principal.getName())
                && storageService.storeAvatarAs(file, newFilename);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
