package org.husky.service;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Process {

    private Log log;

    public Process(Log log){
        this.log = log;
    }

    public void handleProcess(String dir,String... commands) throws IOException, InterruptedException, MojoExecutionException {

        ProcessBuilder processBuilder = new ProcessBuilder(commands);

        processBuilder.directory(Files.createDirectories(Paths.get(dir)).toFile());

        // redirect the log of process that local system runs
        processBuilder.inheritIO().redirectOutput(ProcessBuilder.Redirect.PIPE);

        java.lang.Process process = processBuilder.start();

        process.waitFor();

        if( process.exitValue() !=0 ) {
            BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = buf.readLine()) != null) {
                log.info(line);
            }
            throw new MojoExecutionException(String.format("Process exit value: %s", process.exitValue()));

        }

    }

}
