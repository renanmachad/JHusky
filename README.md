# Description

This project is java  version of (husky)[https://github.com/typicode/husky] and a fork of (jHusky)[https://github.com/Dathin/JHusky]

# How to install
To install in your java project, add this in your 'plugins' section
```
 <plugin>
  <groupId>org.husky</groupId>
  <artifactId>Java-husky</artifactId>
  <version>1.0.0</version>
</plugin>
```

# How to use?
### Install
To use any command that plugin provide, you need 'install'/activate the plugin first.
```
  mvn java-husky:install
```
This will activate the git hooks to your git local repository and create the necessary files

### Configure the git hook
To add a git hook you need specifie if you hook will be activate on a pre commit pre push and etc,
to view all possibilities you can access (this)[https://git-scm.com/docs/githooks] git documentation.
```
 mvn java-husky:add -Dcommand="mvn test" -DhookPath=".husky/pre-commit"
```
In this example, we have added the command ```mvn test ``` to run before a commit will be executed.

### Uninstall
If you do need or if you do not want execute the previus git hooks that configurated,
you can deactive to make your git process without execute the git hooks.
```
  mvn java-husky:uninstall
```

