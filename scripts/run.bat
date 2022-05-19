@echo off
SETLOCAL
echo Running using jdk-13.0.1...

if exist %PROGRAMFILES%\Java\jdk-13.0.1 (
    set PATH=%PROGRAMFILES%\Java\jdk-13.0.1\bin
)

set CLASSPATH=%CD%\..\out;%CD%\..\lib;%CD%\..\bin\lib

java apsoo.Main

echo Running complete.
ENDLOCAL

pause