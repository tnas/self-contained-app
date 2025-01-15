#!/bin/bash

javac -cp "lib/*" -d out src/java/io/github/tnas/webapp/SimpleWebApp.java

java -cp "out:lib/*" io.github.tnas.webapp.SimpleWebApp