@ECHO OFF
CLS
:MENU
ECHO.
ECHO ...............................................
ECHO PRESS 1, 2 , 3 OR 4 to select your task, or 5 to EXIT.
ECHO ...............................................
ECHO.
ECHO 1 - Show  CPU Information
ECHO 2 - Display RAM Information
ECHO 3 - Show Partition Information
ECHO 4 - Show ALL
ECHO 5 - EXIT
ECHO.
SET /P M=Type 1, 2, 3, 4 or 5 then press ENTER:
IF %M%==1 GOTO CPU
IF %M%==2 GOTO RAM
IF %M%==3 GOTO DISK
IF %M%==4 GOTO ALL
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
ECHO(Your CPU model is:
for /f "skip=1 tokens=*" %%f in ('wmic cpu get name') do ECHO( %%f
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
ECHO(Your CPU model is:
for /f "skip=1 tokens=*" %%f in ('wmic cpu get name') do ECHO( %%f
ECHO................................................
ECHO. CPU usage in %%:
for /f "skip=1" %%p in ('wmic cpu get loadpercentage') do ECHO( %%p
GOTO MENU
:DISK
CLS
ECHO................................................
ECHO(This is your Partition Information
ECHO................................................
WMIC LOGICALDISK GET SIZE,FREESPACE,CAPTION
GOTO MENU
