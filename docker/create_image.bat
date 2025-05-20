@echo off
echo Compilando el proyecto con Maven
call mvn clean package -DskipTests

if %ERRORLEVEL% NEQ 0 (
    echo  Error al compilar el proyecto Maven. Abortando.
    exit /b %ERRORLEVEL%
)

echo Compilación Maven completada.

echo Construyendo la imagen Docker

docker build -t guillermolara2004/booknest:1.0.0 -f docker/Dockerfile .

if %ERRORLEVEL% NEQ 0 (
    echo Error al construir la imagen Docker.
    exit /b %ERRORLEVEL%
)

echo Imagen Docker construida correctamente.
