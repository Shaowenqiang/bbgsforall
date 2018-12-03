@echo off
set "Ymd=%date:~,4%%date:~5,2%%date:~8,2%" 
md "D:\backup\%ymd%" 
"C:\Program Files\MySQL\MySQL Server 5.7\bin\mysqldump.exe"  --opt -Q bbgs -ubbgs -p4321`qaz  > D:\backup\%Ymd%\bbgs.sql 