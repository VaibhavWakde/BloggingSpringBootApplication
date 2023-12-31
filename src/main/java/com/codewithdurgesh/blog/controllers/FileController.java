package com.codewithdurgesh.blog.controllers;

import com.codewithdurgesh.blog.payloads.FileResponse;
import com.codewithdurgesh.blog.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/upload")
    public ResponseEntity<FileResponse> fileUpload(
            @RequestParam("image") MultipartFile image
    ) {
        String fileName = null;
        try {
            fileName = this.fileService.uploadImage(path, image);
        }
        catch (IOException e) {
            return new ResponseEntity<>(new FileResponse(null, "Image is not Uploaded due to some server error !!!"),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<FileResponse>(new FileResponse(fileName, "Image is Successfully Uploaded !!!"), HttpStatus.OK);
    }

    //method to serve file
    @GetMapping(value = "/images/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable String imageName,
            HttpServletResponse response
    ) throws IOException {
        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
