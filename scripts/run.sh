#!/bin/zsh

PROJECT_ROOT="${0:A:h:h}"
java -jar $PROJECT_ROOT/target/rot88j-jar-with-dependencies.jar $@
