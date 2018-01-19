@echo off
set attr=""
if "%1"=="-a" (
	set attr=/A
) else if not "%1"=="-l" (
	Set attr=/B
)
dir %attr% 
