echo off

set cp=../lib/*;
set prop=../cfg/automation.properties
set log=../cfg/log4j2.properties
set main=org.testng.TestNG
set suiteDefault=../suits/testng.xml
set suiteParam=%1
if defined suiteParam (
	set suite=%suiteParam%
) else (
	set suite=%suiteDefault%
)

java -cp %cp% -Dautomation.cfg=%prop% -DLogj4=%log% %main%  %suite%