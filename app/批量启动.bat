@echo off  
setlocal EnableDelayedExpansion
title uninstall apks
set ApkPath=%cd%\
cd %ApkPath%  
for /R %%s in (*.apk) do ( 
    aapt dump badging %%s  > %cd%\2.txt
    findstr "package" %cd%\2.txt  >> %cd%\1.txt
)  
del %cd%\2.txt
:: 每行以空格为分割，获取第二个元素
for /f "tokens=2 delims= " %%s in (1.txt) do (  
   echo %%s >> 2.txt
) 
 del %cd%\1.txt
 :: 每行以=号为分割，获取第二个元素，并且去除单引号’，获取最终的包名
for /f "tokens=2 delims==" %%s in (2.txt) do (  
   set s1=%%s 
   set s1=!s1:'=!
   echo !s1! >> 3.txt
) 
del %cd%\2.txt
for /f %%s in (3.txt) do (  
    nox_adb -s 127.0.0.1:62025 shell am start -n %%s/com.carl.mvpdemo.module.home.view.MainActivity
) 
del %cd%\3.txt
pause