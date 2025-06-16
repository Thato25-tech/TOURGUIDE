@echo off
set JAVA_HOME=C:\Program Files\Java\jdk-23
set PATH=%JAVA_HOME%\bin;%PATH%

java --module-path "build/libs/lesotho-tour-guide-1.0.jar" ^
     --add-modules javafx.controls,javafx.fxml,javafx.media ^
     -jar build/libs/lesotho-tour-guide-1.0.jar 