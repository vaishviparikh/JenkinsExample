@echo off

echo ================================
echo Starting Backend...
echo ================================

cd MiniJobPortal-Backend
start cmd /k java -jar MiniJobPortal-0.0.1-SNAPSHOT.jar

echo Waiting for backend to start...
timeout /t 5

echo ================================
echo Starting Angular Frontend...
echo ================================

cd ../MiniJobPortal-Frontend
start cmd /k ng serve

echo ================================
echo Application Started
echo Backend: http://localhost:1111
echo Frontend: http://localhost:4200
echo ================================