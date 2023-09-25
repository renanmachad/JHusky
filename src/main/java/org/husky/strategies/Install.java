package org.husky.strategies;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.husky.service.Process;
import org.husky.utils.HuskyUtils;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Mojo(name = "install")
public class Install extends Commands {

    private final String HUSKY_DIR = "_";

    private Process process = new Process(getLog());


    public Install(){

    }

    @Override
    void command() throws MojoExecutionException, IOException, InterruptedException {
        String DIR = ".husky";
        process.handleProcess(DIR,"git","rev-parse");

        prepareEnv(DIR);

        createHuskyFiles(DIR);

        process.handleProcess(DIR, "git","config","core.hooksPath", DIR);

        getLog().info("Git hooks installed successfully");

    }



    private void prepareEnv(String dir) throws IOException {
        Files.createDirectories(Paths.get(dir));
        Files.deleteIfExists(Paths.get(dir, HUSKY_DIR, ".gitignore"));
        Files.deleteIfExists(Paths.get(dir, HUSKY_DIR, "husky.sh"));
        Files.createDirectories(Paths.get(dir,HUSKY_DIR));
    }


    private void createHuskyFiles(String dir) throws IOException{
        Path huskySh = Files.createFile(Paths.get(dir, HUSKY_DIR,"husky.sh"));

        huskySh.toFile().setExecutable(true);

        Files.write(huskySh, HuskyUtils.generateHuskyScript().getBytes());

        gitIgnoreScript(dir);
    }

    private void gitIgnoreScript(String directory) throws IOException {
        Path gitignore = Files.createFile(Paths.get(directory, HUSKY_DIR, ".gitignore"));
        Files.write(gitignore, "*".getBytes());
    }

    @Override
    String getCommandName() {
        return "Install";
    }
}
