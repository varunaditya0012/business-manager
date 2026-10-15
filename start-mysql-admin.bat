@echo off
:: Batch script to start MySQL80 service with Administrator elevation
echo Requesting administrator privileges to start MySQL80 service...
powershell -Command "Start-Process powershell -ArgumentList '-NoProfile -Command \"Start-Service MySQL80; Get-Service MySQL80 | Format-Table -AutoSize; Start-Sleep -Seconds 3\"' -Verb RunAs"
echo Done. You can check MySQL service status with: Get-Service MySQL80
pause
