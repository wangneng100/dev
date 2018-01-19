@echo off
setlocal
set parent_dir=%cd%

if "%2"=="" (
	if "%1"=="" (
		@echo "Usage: hid [parent_dir] <dir_or_file_to_hide> [-f]"
		@echo -f means hide file
		@echo If parent_dir is not set, current path will be used as parent dir
		exit /b
	) else (
		set dir_to_hide=%1
	)
) else if "%2"=="-f" (
	call ant hideFile -f %~dp0/ant/myant.xml -Dtarget.dir=%parent_dir% -Dfile=%1
) else (
	set parent_dir=%1
	set dir_to_hide=%2
)
call ant hideDir -f %~dp0/ant/myant.xml -Dtarget.dir=%parent_dir% -Ddir=%dir_to_hide%
endlocal