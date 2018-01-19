@echo off
setlocal
if "%2"=="-a" (
	call ant showAllDir -f %~dp0/ant/myant.xml -Dtarget.dir=%cd% -Ddir=%1
) else (
	call ant showFileOrDir -f %~dp0/ant/myant.xml -Dtarget.dir=%cd% -Dfile=%1
)

endlocal