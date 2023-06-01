package com.Abdessalam.friendMA.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class FileUtil {

    private FileUtil(){

    }
    public static final String folderPath =  "uploads//";
    public static final Path filePath = Paths.get(folderPath);
    public static final String avatarFolderPath = folderPath + "avatars//";
    public static final Path avatarFilePath = Paths.get(avatarFolderPath);

    public static String getExtension(String originalFilename) {
        return originalFilename.substring(originalFilename.lastIndexOf("."));
    }

    public static void createDirIfNotExist() {
        if (!Files.exists(filePath)) {
            try {
                Files.createDirectories(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

   // create avatar folder if not exist inside uploads folder
    public static void createAvatarDirIfNotExist() {
        if (!Files.exists(avatarFilePath)) {
            try {
                Files.createDirectories(avatarFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
