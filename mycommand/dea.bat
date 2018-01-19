@echo off
setlocal
set parent_dir=%cd%
if "%1"=="" (
	@echo "Usage: dd [parent_dir] <dir_to_del>"
	@echo If parent_dir is not set, current path will be used as parent dir. -a to delete all 
	exit /b
) else (
	set dir_to_del=%1
)

if "%2"=="" (
	@echo 
) else if "%2"=="-a" (
	call ant deleteAll -f %~dp0/ant/myant.xml -Dtarget.dir=%parent_dir% -Ddir=%dir_to_del%
	exit /b
) else (
	set parent_dir=%2	
)
call ant delete -f %~dp0/ant/myant.xml -Dtarget.dir=%parent_dir% -Ddir=%dir_to_del%
endlocal