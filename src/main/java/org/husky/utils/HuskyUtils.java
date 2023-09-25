package org.husky.utils;

public  class HuskyUtils {

    public HuskyUtils(){ /* TODO document why this constructor is empty */ }

    public static String generateHuskyScript() {
        String fiChar = "  fi\n";
        return "#!/bin/sh\n" +
                "if [ -z \"$husky_skip_init\" ]; then\n" +
                "  debug () {\n" +
                "    if [ \"$HUSKY_DEBUG\" = \"1\" ]; then\n" +
                "      echo \"husky (debug) - $1\"\n" +
                "    fi\n" +
                "  }\n" +
                "\n" +
                "  readonly hook_name=\"$(basename \"$0\")\"\n" +
                "  debug \"starting $hook_name...\"\n" +
                "\n" +
                "  if [ \"$HUSKY\" = \"0\" ]; then\n" +
                "    debug \"HUSKY env variable is set to 0, skipping hook\"\n" +
                "    exit 0\n" +
                fiChar +
                "\n" +
                "  if [ -f ~/.huskyrc ]; then\n" +
                "    debug \"sourcing ~/.huskyrc\"\n" +
                "    . ~/.huskyrc\n" +
                fiChar +
                "\n" +
                "  export readonly husky_skip_init=1\n" +
                "  sh -e \"$0\" \"$@\"\n" +
                "  exitCode=\"$?\"\n" +
                "\n" +
                "  if [ $exitCode != 0 ]; then\n" +
                "    echo \"husky - $hook_name hook exited with code $exitCode (error)\"\n" +
                fiChar +
                "\n" +
                "  exit $exitCode\n" +
                "fi\n";
    }
}
