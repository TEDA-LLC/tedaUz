@echo off
for /f %%i IN ('"mvn help:evaluate -Dexpression=project.name -q -DforceStdout"') do set project_name=%%i
for /f %%i IN ('"mvn help:evaluate -Dexpression=project.version -q -DforceStdout"') do set project_version=%%i
@echo run %project_name%-%project_version%
title %project_name%-%project_version%
java -jar target/%project_name%-%project_version%.jar