#!/bin/zsh

PROJECT_ROOT="${0:A:h:h}"
cd $PROJECT_ROOT
mvn clean install
