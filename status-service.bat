@echo off
setlocal
echo ===================================================
echo     Business Tool Manager - Service Status
echo ===================================================
echo.

:: Check port 8080
netstat -ano | findstr :8080 | findstr LISTENING >nul 2>&1
if %ERRORLEVEL% equ 0 (
    echo [STATUS] LIVE and RUNNING on http://localhost:8080
    echo.
    echo Active Network Connections:
    netstat -ano | findstr :8080 | findstr LISTENING
    echo.
    echo Running Java Processes:
    tasklist /FI "IMAGENAME eq java.exe"
) else (
    echo [STATUS] STOPPED (Port 8080 is inactive)
)

echo.
if exist "D:\business-manager\logs\app.log" (
    echo Last 10 lines of application log:
    echo ---------------------------------------------------
    powershell -Command "Get-Content 'D:\business-manager\logs\app.log' -Tail 10 -ErrorAction SilentlyContinue"
)

echo.
pause
