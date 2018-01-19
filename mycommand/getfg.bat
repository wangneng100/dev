@echo off
setlocal
set current_dir=%cd%

if "%1"=="" (
	set work_dir=%current_dir%

) else (
	set work_dir=%1
)
call groovy %~dp0/groovy/getFeature %work_dir%

endlocal