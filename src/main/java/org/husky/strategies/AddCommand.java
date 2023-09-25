package org.husky.strategies;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import static org.husky.service.FileService.checkDirectoryExists;

@Mojo(name = "add")
public class AddCommand extends Commands {

    @Parameter(property = "hookPath")
    private String hookPath;

    @Parameter(property = "command")
    private String command;

    public void setCommand(String command){
        this.command = command;
    }

    public void setHookPath(String hookPath){
        this.hookPath = hookPath;
    }

    public AddCommand(){

    }

    @Override
    void command() throws MojoExecutionException, IOException, InterruptedException {
        Path path = Paths.get(hookPath);

        checkDirectoryExists(path);

        create(path, command);
    }


    private void create(Path path, String command) throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
        }

        path.toFile().setExecutable(true);
        Files.write(path, Collections.singletonList(defaultFileScript() + command));
        System.out.println("Command " + command + " created successfully");
        getLog().info("Command " + command + " created successfully");
    }


    /**
     * @description - TO run a git  hook is necessary use some commands ro run
     *  -#!/bin/sh
     * command {command inputed by user here}
     * to git hook works
     * @return {String}
     */
    private  String defaultFileScript(){
        return "#!/bin/sh \ncommand ";
    }
    @Override
    String getCommandName() {
        return "add";
    }
}
