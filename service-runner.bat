@echo off
setlocal
cd /d "D:\business-manager"

:: Set Java runtime
if exist "F:\jdk\bin\java.exe" (
    set "JAVA_EXE=F:\jdk\bin\java.exe"
) else if exist "C:\Program Files\Java\jdk-25\bin\java.exe" (
    set "JAVA_EXE=C:\Program Files\Java\jdk-25\bin\java.exe"
) else (
    set "JAVA_EXE=java"
)

:: Create logs directory
if not exist "logs" mkdir "logs"

:: Check if MySQL is running on port 3306
netstat -ano | findstr :3306 >nul 2>&1
if %ERRORLEVEL% equ 0 (
    echo [%date% %time%] Starting with MySQL production configuration... >> logs\service.log
    "%JAVA_EXE%" -jar "D:\business-manager\target\business-manager-1.0.0-SNAPSHOT.jar" >> logs\app.log 2>&1
) else (
    echo [%date% %time%] MySQL not running. Starting with Zero-Config embedded DB profile... >> logs\service.log
    "%JAVA_EXE%" -Dspring.profiles.active=dev -jar "D:\business-manager\target\business-manager-1.0.0-SNAPSHOT.jar" >> logs\app.log 2>&1
)
