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

@echo off

SETLOCAL
echo Starting deploying...

if exist %PROGRAMFILES%\Java\jdk-13.0.1 (
    set PATH=%PROGRAMFILES%\Java\jdk-13.0.1\bin;%PATH%
    set JAVA_HOME=%PROGRAMFILES%\Java\jdk-13.0.1;%JAVA_HOME%
)

set TEMPD=%CD%

cd %CD%\..\out
echo Creating APSOO_G10_SisLoc.jar...
jar cfme APSOO_G10_SisLoc.jar %TEMPD%\..\configs\META-INF\MANIFEST.MF apsoo.Main apsoo

if not exist %TEMPD%\..\bin mkdir %TEMPD%\..\bin
move /y APSOO_G10_SisLoc.jar %TEMPD%\..\bin\APSOO_G10_SisLoc.jar >NUL
echo File .jar created.

echo Checking for sqlite-jdbc-3.36.0.3.jar dependency...
@cd %TEMPD%\..\bin
if not exist lib (mkdir lib)
cd lib

if not exist %TEMPD%\..\bin\config.properties copy %TEMPD%\..\configs\config.properties %TEMPD%\..\bin

if not exist sqlite-jdbc-3.36.0.3.jar (
    echo Downloading sqlite-jdbc-3.36.0.3.jar...
    curl -o sqlite-jdbc-3.36.0.3.jar https://github.com/xerial/sqlite-jdbc/releases/download/3.36.0.3/sqlite-jdbc-3.36.0.3.jar
    echo Downloaded sqlite-jdbc-3.36.0.3.jar.
)
echo Dependency checked.

cd %TEMPD%

echo Deploy complete.
ENDLOCAL
