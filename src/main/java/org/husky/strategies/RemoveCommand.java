package org.husky.strategies;


import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.husky.service.FileService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.husky.service.FileService.checkDirectoryExists;

@Mojo(name = "remove-command")
public class RemoveCommand extends Commands {

    @Parameter(property = "hookPath")
    private String hookPath;

    @Parameter(property = "command")
    private String command;

    public void setHookPath(String hookPath) {
        this.hookPath = hookPath;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public void command() throws MojoExecutionException, IOException, InterruptedException {
        Path path = Paths.get(hookPath);
        checkDirectoryExists(path);
        remove(path);
    }


    private void remove(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        String content = lines.stream()
                .filter(line -> !line.contains(command))
                .collect(Collectors.joining("\n")); // Join the lines back into a single string


        // save the updated file
        Files.write(path,content.getBytes());

        getLog().info(String.format("Command %s removed successfully from hook %s!", command,hookPath));

    }

    @Override
    String getCommandName() {
        return "remove-command";
    }
}
