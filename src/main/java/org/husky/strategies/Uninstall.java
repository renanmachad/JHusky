package org.husky.strategies;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.husky.service.Process;

import java.io.IOException;


@Mojo(name = "uninstall")
public class Uninstall extends Commands{

    private final String DIR = ".husky";

    private Process process = new Process(getLog());


    public Uninstall(){}

    @Override
    void command() throws MojoExecutionException, IOException, InterruptedException {
        process.handleProcess(DIR,"git","config","--unset","core.hooksPath" );
        getLog().info("Uninstall successfully!");
    }

    @Override
    String getCommandName() {
        return "uninstall";
    }
}
