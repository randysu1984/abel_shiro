@echo off

cd ..
call mvn mybatis-generator:generate
cd mvn-bin
pause