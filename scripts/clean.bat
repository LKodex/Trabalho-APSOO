@echo off
echo Cleaning...
if exist %CD%\..\out (
    echo Cleaning \out folder...
    @rd /s /q %CD%\..\out
    echo \out folder clear.
)
if exist %CD%\..\bin (
    echo Cleaning \in folder...
    @rd /s /q %CD%\..\bin
    echo \bin folder clear.
)
echo Cleaning complete.
