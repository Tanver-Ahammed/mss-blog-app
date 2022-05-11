package com.spring.boot.blog.services.impl;

import com.spring.boot.blog.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile multipartFile) throws IOException {

        // file name
        String name = multipartFile.getOriginalFilename();

        // random name generate file name
        String randomId = UUID.randomUUID().toString();
        assert name != null;
        String fileName = randomId.concat(name.substring(name.lastIndexOf(".")));

        // full path
        String filePath = path + File.separator + fileName;

        // generate folder if you not created
        File file = new File(path);

        if (!file.exists())
            file.mkdir();

        // file copy
        Files.copy(multipartFile.getInputStream(), Paths.get(filePath));
        return name;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        return new FileInputStream(fullPath);
    }
}
