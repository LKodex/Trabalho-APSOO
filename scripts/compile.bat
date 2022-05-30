@echo off

SETLOCAL
echo Trying to compile using jdk-13.0.1...

if exist %PROGRAMFILES%\Java\jdk-13.0.1 (
    set PATH=%PROGRAMFILES%\Java\jdk-13.0.1\bin;%PATH%
    set JAVA_HOME=%PROGRAMFILES%\Java\jdk-13.0.1;%JAVA_HOME%
)

for /r %CD%\..\src %%f in (*.java) do javac -encoding utf8 -source 13 -target 13 %%f -d %CD%\..\out -sourcepath %CD%\..\src

echo Compilation complete.
ENDLOCAL

pause
