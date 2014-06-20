@echo off
del ..\lib\*.jar
call mvn validate
copy lib\* ..\lib
move tmp\* ..\lib
