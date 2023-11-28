package org.spring.boot.nested;

import org.jobrunr.storage.sql.h2.H2StorageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller {

    private static FileSystem fileSystem;

    @GetMapping("/get/from/nested/jar")
    public String getFromNestedJar() throws IOException, URISyntaxException {
        loadFileSystemIfNecessary();
        Path path = fileSystem.getPath("BOOT-INF/lib/jobrunr-6.3.3.jar!/org/jobrunr/storage/sql/common/migrations/");
        boolean exists = Files.exists(path);
        List<Path> list = Files.list(path).collect(Collectors.toList());
        return "Root exists: " + exists + "; contents: " + list;
    }

    private static void loadFileSystemIfNecessary() throws IOException, URISyntaxException {
        if(fileSystem == null) {
            var url = ((JarURLConnection) H2StorageProvider.class.getResource("migrations").openConnection()).getJarFileURL();
            fileSystem = FileSystems.newFileSystem(url.toURI(), Collections.emptyMap());
        }
    }

}
