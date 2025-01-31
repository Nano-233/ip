@ECHO OFF

REM Create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM Delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM Compile the Java source files inside the luna package structure
javac -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\luna\*.java ..\src\main\java\luna\command\*.java ..\src\main\java\luna\storage\*.java ..\src\main\java\luna\task\*.java ..\src\main\java\luna\ui\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM Run the program, feed commands from input.txt and redirect output to ACTUAL.TXT
java -classpath ..\bin luna.Luna < input.txt > ACTUAL.TXT

REM Compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
IF ERRORLEVEL 1 (
    echo ********** TEST FAILURE **********
    echo Differences detected between ACTUAL.TXT and EXPECTED.TXT.
) ELSE (
    echo ********** TEST SUCCESS **********
)

pause
