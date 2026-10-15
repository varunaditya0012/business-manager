@echo off
setlocal
title Business Tool Manager - Launcher

echo ===================================================
echo        Business Tool Manager - Application
echo ===================================================
echo.

:: Ensure Java and Maven paths
if not defined JAVA_HOME (
    if exist "F:\jdk" (
        set "JAVA_HOME=F:\jdk"
    ) else if exist "C:\Program Files\Java\jdk-25" (
        set "JAVA_HOME=C:\Program Files\Java\jdk-25"
    )
)

if defined JAVA_HOME (
    set "PATH=%JAVA_HOME%\bin;%PATH%"
)

if exist "D:\tools\apache-maven-3.9.9\bin" (
    set "PATH=D:\tools\apache-maven-3.9.9\bin;%PATH%"
)

echo [1/2] Checking environment...
java -version
if %ERRORLEVEL% neq 0 (
    echo [ERROR] Java is not available. Please install Java 17+.
    pause
    exit /b 1
)

echo.
echo [2/2] Starting application...
echo Application will be available at: http://localhost:8080
echo Login credentials: admin / admin123
echo Press Ctrl+C to stop.
echo ---------------------------------------------------

:: Check if MySQL is running on port 3306
netstat -ano | findstr :3306 >nul 2>&1
if %ERRORLEVEL% equ 0 (
    echo MySQL detected running on port 3306. Starting with MySQL profile...
    call mvnw.cmd spring-boot:run
) else (
    echo MySQL is not currently running. Starting with instant Zero-Config Dev profile (embedded DB)...
    call mvnw.cmd spring-boot:run "-Dspring-boot.run.profiles=dev"
)

pause
