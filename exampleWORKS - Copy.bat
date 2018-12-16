@ECHO OFF
CLS
:MENU
ECHO.
ECHO ...............................................
ECHO PRESS 1, 2 , 3 OR 4 to select your task, or 5 to EXIT.
ECHO ...............................................
ECHO.
ECHO 1 - Open CPU
ECHO 2 - Display RAM
ECHO 3 - Show ALL
ECHO 4 - DISK
ECHO 5 - EXIT
ECHO.
SET /P M=Type 1, 2, 3, or 4 then press ENTER:
IF %M%==1 GOTO CPU
IF %M%==2 GOTO RAM
IF %M%==3 GOTO ALL
IF %M%==4 GOTO DISK
IF %M%==5 GOTO EOF
:ALL
CLS
ECHO You Have chosen Display ALL
ECHO Below is your RAM information:
ECHO...............................................
for /f "tokens=4" %%a in ('systeminfo ^| findstr Physical') do if defined totalMem (set availableMem=%%a) else (set totalMem=%%a)
set totalMem=%totalMem:,=%
set availableMem=%availableMem:,=%
set /a usedMem=totalMem-availableMem
Echo Total Memory: %totalMem%
Echo Used Memory: %usedMem%
echo Available Memory: %availableMem%
ECHO................................................
ECHO(This is your CPU information
ECHO................................................
ECHO. CPU usage in %%:
for /f "skip=1" %%p in ('wmic cpu get loadpercentage') do ECHO( %%p
ECHO................................................
ECHO(This is your Disk Information
ECHO................................................
WMIC LOGICALDISK GET SIZE,FREESPACE,CAPTION
GOTO MENU
:RAM
CLS
ECHO You have chosen RAM. Below is your information:
ECHO................................................
for /f "tokens=4" %%a in ('systeminfo ^| findstr Physical') do if defined totalMem (set availableMem=%%a) else (set totalMem=%%a)
set totalMem=%totalMem:,=%
set availableMem=%availableMem:,=%
set /a usedMem=totalMem-availableMem
Echo Total Memory: %totalMem%
Echo Used Memory: %usedMem%
echo Available Memory: %availableMem%
GOTO MENU
:CPU
CLS
ECHO(This is your CPU information
ECHO................................................
ECHO. CPU usage in %%:
for /f "skip=1" %%p in ('wmic cpu get loadpercentage') do ECHO( %%p
GOTO MENU
:DISK
WMIC LOGICALDISK GET SIZE,FREESPACE,CAPTION
GOTO MENU
