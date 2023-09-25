package org.husky.strategies;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.IOException;

public abstract  class Commands extends AbstractMojo {

    public void execute() throws MojoExecutionException {
        try {
            command();
        }catch (Exception e) {
            getLog().error(e);
            throw  new MojoExecutionException("Unable to run goal "+getCommandName());
        }
    }

    abstract void command() throws MojoExecutionException, IOException, InterruptedException;

    abstract String getCommandName();
}
