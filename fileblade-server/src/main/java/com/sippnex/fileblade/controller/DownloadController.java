package com.sippnex.fileblade.controller;

import com.sippnex.fileblade.domain.FbFile;
import com.sippnex.fileblade.exception.FbFileNotFoundException;
import com.sippnex.fileblade.service.FilebladeService;
import com.sippnex.fileblade.util.PathUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/fileblade/download")
public class DownloadController {

    private final FilebladeService filebladeService;

    public DownloadController(FilebladeService filebladeService) {
        this.filebladeService = filebladeService;
    }

    @GetMapping("**")
    public void download(HttpServletRequest request, HttpServletResponse response) throws FbFileNotFoundException, IOException {
        String pathString = PathUtils.getPathFromRequest(request);
        FbFile file = filebladeService.readFile(pathString).orElseThrow(FbFileNotFoundException::new);
        IOUtils.copy(new ByteArrayInputStream(file.getContent()), response.getOutputStream());
    }

}
