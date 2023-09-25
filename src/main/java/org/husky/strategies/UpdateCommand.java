package org.husky.strategies;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.husky.service.FileService.checkDirectoryExists;

@Mojo(name = "update-command")
public class UpdateCommand extends Commands {

    @Parameter(property = "hookPath")
    private String hookPath;

    @Parameter(property = "command")
    private String command;

    @Parameter(property = "newCommand")
    private String newCommand;

    @Override
    void command() throws MojoExecutionException, IOException, InterruptedException {
        Path path = Paths.get(hookPath);
        verifyCommand();
        checkDirectoryExists(path);

        update(path);
    }

    private void update(Path path) throws IOException {
        // read lines
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

        String content = lines.stream()
                .map(line -> line.replaceAll(command,newCommand))
                .collect(Collectors.joining("\n"));

        Files.write(path, content.getBytes());

        getLog().info(String.format("Command %s updated successfully to %s from hook %s!", command,newCommand,hookPath));

    }

    private void verifyCommand() throws MojoExecutionException {
        if(Objects.equals(newCommand, "#!/bin/sh ")) {
            throw  new MojoExecutionException(String.format("Command %s is not allowed to updated", newCommand));
        }
    }


    @Override
    String getCommandName() {
        return "update-command";
    }
}
