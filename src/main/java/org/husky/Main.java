package org.husky;

import org.apache.maven.plugin.MojoExecutionException;
import org.husky.strategies.AddCommand;
import org.husky.strategies.Install;
import org.husky.strategies.RemoveCommand;

public class Main {
    public static void main(String[] args) throws MojoExecutionException {
        // install
        var install = new Install();
        install.execute();


        // add command
        var command = new AddCommand();
        command.setHookPath(".husky/pre-commit");
        command.setCommand("mvn install");
        command.execute();

        // remove command
        var remove = new RemoveCommand();
        remove.setHookPath(".husky/pre-commit");
        remove.setCommand("mvn install");
        remove.execute();

        // add new command to test format of file after remove
        command.setHookPath(".husky/pre-commit");
        command.setCommand("mvn install");
        command.execute();
    }
}