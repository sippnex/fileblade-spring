package com.sippnex.fileblade.controller;

import com.sippnex.fileblade.domain.FbDirectory;
import com.sippnex.fileblade.exception.FbDirectoryNotFoundException;
import com.sippnex.fileblade.service.FilebladeService;
import com.sippnex.fileblade.util.PathUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/fileblade/browse")
public class BrowseController {

    private final FilebladeService filebladeService;

    public BrowseController(FilebladeService filebladeService) {
        this.filebladeService = filebladeService;
    }

    @GetMapping("**")
    public FbDirectory readDirectory(HttpServletRequest request) throws FbDirectoryNotFoundException, IOException {
        String pathString = PathUtils.getPathFromRequest(request);
        return filebladeService.readDirectory(pathString).orElseThrow(FbDirectoryNotFoundException::new);
    }

    @PostMapping("**")
    public void createDirectory(HttpServletRequest request) throws IOException {
        String pathString = PathUtils.getPathFromRequest(request);
        filebladeService.createDirectory(pathString);
    }

    @PutMapping("**")
    public void moveElement(HttpServletRequest request, @RequestParam("target") String target) throws IOException {
        String pathString = PathUtils.getPathFromRequest(request);
        filebladeService.moveElement(pathString, target);
    }

    @DeleteMapping("**")
    public void deleteElement(HttpServletRequest request) throws IOException {
        String pathString = PathUtils.getPathFromRequest(request);
        filebladeService.deleteElement(pathString);
    }

}
