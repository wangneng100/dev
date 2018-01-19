@echo off
C:
if "%1"=="" (
    gobep&&run
) else (
	gobep&&debug
)
