@echo off
setlocal
set current_dir=%cd%
if "%3"=="" (
	@echo "Usage: rep <source_str> <target_str> <suffix>"
	@echo replace source_str with target_str in *.suffix
	exit /b
) else (
	call ant replace -f %~dp0/ant/myant.xml -Dtarget.dir=%current_dir% -Dsource=%1 -Dtarget=%2 -Dsuffix=%3
) 
endlocal