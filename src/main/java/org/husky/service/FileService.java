package org.husky.service;

import org.apache.maven.plugin.MojoExecutionException;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileService {

    public FileService(){}

    public static void checkDirectoryExists(Path path) throws MojoExecutionException {
        if (!Files.exists(path.getParent())) {
            throw new MojoExecutionException(String.format("Can't find hook %s, try run install goal 'mvn Java-husky:install' ", path));
        }
    }
}
