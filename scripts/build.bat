@echo off

SETLOCAL
echo Starting deploying...

if exist %PROGRAMFILES%\Java\jdk-13.0.1 (
    set PATH=%PROGRAMFILES%\Java\jdk-13.0.1\bin;%PATH%
    set JAVA_HOME=%PROGRAMFILES%\Java\jdk-13.0.1;%JAVA_HOME%
)

set TEMPD=%CD%

cd %CD%\..\out
echo Creating APSOO_Exercicio.jar...
jar cfme APSOO_Exercicio.jar %TEMPD%\..\configs\META-INF\MANIFEST.MF apsoo.Main apsoo

if not exist %TEMPD%\..\bin mkdir %TEMPD%\..\bin
move /y APSOO_Exercicio.jar %TEMPD%\..\bin\APSOO_Exercicio.jar >NUL
echo File .jar created.

echo Checking for postgresql-42.3.5.jar dependency...
@cd %TEMPD%\..\bin
if not exist lib (mkdir lib)
cd lib

if not exist %TEMPD%\..\bin\config.properties copy %TEMPD%\..\configs\config.properties %TEMPD%\..\bin

if not exist postgresql-42.3.5.jar (
    echo Downloading postgresql-42.3.5.jar...
    curl -o postgresql-42.3.5.jar https://jdbc.postgresql.org/download/postgresql-42.3.5.jar
    echo Downloaded postgresql-42.3.5.jar.
)
echo Dependency checked.

cd %TEMPD%

echo Deploy complete.
ENDLOCAL
