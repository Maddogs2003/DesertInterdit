@echo off
cd ..
REM Nettoyage des anciens fichiers .class
del /s *.class

REM Compilation de tous les fichiers .java
javac -d out *.java

cd out
echo Compilation terminée.
java proj
pause
