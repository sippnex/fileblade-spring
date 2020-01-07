package com.sippnex.fileblade.controller;

import com.sippnex.fileblade.service.FilebladeService;
import com.sippnex.fileblade.util.PathUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/fileblade/upload")
public class UploadController {


    private final FilebladeService filebladeService;

    public UploadController(FilebladeService filebladeService) {
        this.filebladeService = filebladeService;
    }

    @PostMapping("**")
    public void upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        String pathString = PathUtils.getPathFromRequest(request);
        filebladeService.writeFile(pathString, file.getBytes());
    }

}
