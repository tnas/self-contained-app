#!/bin/bash

wget https://builds.openlogic.com/downloadJDK/openlogic-openjdk/11.0.25+9/openlogic-openjdk-11.0.25+9-linux-x64.tar.gz
tar -xvf openlogic-openjdk-11.0.25+9-linux-x64.tar.gz -C jdk/linux
mv jdk/linux/openlogic-openjdk-11.0.25+9-linux-x64 openjdk-11

JDK_DIR="jdk/openjdk-11/bin"

${JDK_DIR}/javac -cp "lib/*" -d out src/java/io/github/tnas/webapp/SimpleWebApp.java

${JDK_DIR}/java -cp "out:lib/*" io.github.tnas.webapp.SimpleWebApp