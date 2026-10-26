@echo off
setlocal
echo Stopping Business Tool Manager background service...

:: Find PID using port 8080
for /f "tokens=5" %%a in ('netstat -aon ^| findstr :8080 ^| findstr LISTENING') do (
    echo Terminating PID: %%a
    taskkill /F /PID %%a >nul 2>&1
)

:: Also kill any remaining business-manager java processes
wmic process where "CommandLine like '%%business-manager-1.0.0-SNAPSHOT.jar%%'" call terminate >nul 2>&1

echo Service stopped.
pause
