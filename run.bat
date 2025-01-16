@echo off

SET "JDK_NAME=openlogic-openjdk-11.0.25+9-windows-x64"
SET "JDK_ZIP=%JDK_NAME%.zip"
SET "JDK_DIR=jdk\windows"
SET "JDK_HOME=%JDK_DIR%\openjdk-11"
SET "JDK_BIN=%JDK_HOME%\bin"

IF NOT EXIST %JDK_HOME% (
  CURL --output %JDK_ZIP% "https://builds.openlogic.com/downloadJDK/openlogic-openjdk/11.0.25+9/%JDK_ZIP%"
  TAR "-xvf" "%JDK_ZIP%" "-C" "%JDK_DIR%"
  REN "%JDK_DIR%\%JDK_NAME%" "openjdk-11"
  DEL "%JDK_ZIP%"
)

%JDK_BIN%\javac -cp lib\* -d out src\io\github\tnas\webapp\SimpleWebApp.java
%JDK_BIN%\java -cp out;lib\* io.github.tnas.webapp.SimpleWebApp