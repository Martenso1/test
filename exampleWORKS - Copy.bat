ECHO OFF
CLS
fsutil fsinfo drives>drives.tmp
:MENU
ECHO.
ECHO ...............................................
ECHO PRESS 1, 2 OR 3 to select your task, or 4 to EXIT.
ECHO ...............................................
ECHO.
ECHO 1 - Open CPU
ECHO 2 - Display RAM
ECHO 3 - Show ALL
ECHO 4 - EXIT
ECHO.
SET /P M=Type 1, 2, 3, or 4 then press ENTER:
IF %M%==1 GOTO CPU
IF %M%==2 GOTO RAM
IF %M%==3 GOTO ALL
IF %M%==4 GOTO EOF
IF %M%==5 GOTO DISK
:ALL
CLS
for /f "tokens=4" %%a in ('systeminfo ^| findstr Physical') do if defined totalMem (set availableMem=%%a) else (set totalMem=%%a)
set totalMem=%totalMem:,=%
set availableMem=%availableMem:,=%
set /a usedMem=totalMem-availableMem
Echo Total Memory: %totalMem%
Echo Used Memory: %usedMem%
echo Available Memory: %availableMem%
for /f "skip=1" %%p in ('wmic cpu get loadpercentage') do echo Current Usage: %%p
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
for /f "skip=1" %%p in ('wmic cpu get loadpercentage') do echo Current CPU Usage: %%p 
GOTO MENU
:DISK
FOR /f "tokens=1*delims=:" %%i IN ('fsutil volume diskfree %volume%') DO (
    SET "diskfree=!disktotal!"
    SET "disktotal=!diskavail!"
    SET "diskavail=%%j")
FOR /f "tokens=1,2" %%i IN ("%disktotal% %diskavail%") DO SET "disktotal=%%i"& SET "diskavail=%%j"
ECHO Information for volume %volume%
ECHO total  %disktotal:~0,-9% GB
ECHO avail. %diskavail:~0,-9% GB
GOTO MENU