package com.sippnex.fileblade.service;

import com.sippnex.fileblade.domain.FbDirectory;
import com.sippnex.fileblade.domain.FbElement;
import com.sippnex.fileblade.domain.FbFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class FilebladeService {

    @Value("${fileblade.root:fileblade}")
    private String rootPath;

    public Optional<? extends FbElement> readElement(String pathString) throws IOException {
        Path path = Paths.get(rootPath + pathString);
        if (Files.isDirectory(path)) {
            return readDirectory(pathString);
        } else {
            return readFile(pathString);
        }
    }

    public Optional<FbDirectory> readDirectory(String pathString) throws IOException {
        Path path = Paths.get(rootPath + pathString);
        Set<FbElement> elements = new HashSet<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path elementPath : stream) {
                FbElement element;
                String elementPathString = elementPath.toString().substring(rootPath.length());
                if(Files.isDirectory(elementPath)) {
                    element = new FbDirectory(elementPathString);
                } else {
                    element = new FbFile(elementPathString);
                }
                elements.add(element);
            }
        } catch (NoSuchFileException | NotDirectoryException e) {
            return Optional.empty();
        }
        return Optional.of(new FbDirectory(pathString, elements));
    }

    public Optional<FbFile> readFile(String pathString) throws IOException {
        Path path = Paths.get(rootPath + pathString);
        if(!Files.exists(path) || Files.isDirectory(path)) {
            return Optional.empty();
        }
        return Optional.of(new FbFile(pathString, Files.readAllBytes(path)));
    }

    public void createDirectory(String pathString) throws IOException {
        Path path = Paths.get(rootPath + pathString);
        Files.createDirectory(path);
    }

    public void writeFile(String pathString, byte[] content) throws IOException {
        Path path = Paths.get(rootPath + pathString);
        Files.write(path, content);
    }

    public void moveElement(String sourcePathString, String targetPathString) throws IOException {
        Path sourcePath = Paths.get(rootPath + sourcePathString);
        Path targetPath = Paths.get(rootPath + targetPathString);
        Files.move(sourcePath, targetPath);
    }

}
