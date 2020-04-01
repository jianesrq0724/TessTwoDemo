@echo off
set work_path=%cd%\
cd /d %work_path%   
for /R %%s in (*.apk) do (  
nox_adb -s 127.0.0.1:62025 install "%%s"  
)  
pause