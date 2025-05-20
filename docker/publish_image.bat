@echo off
echo Subiendo imagen a Docker Hub
docker push guillermolara2004/booknest:1.0.0

if %ERRORLEVEL% NEQ 0 (
    echo Error al subir la imagen.
    exit /b %ERRORLEVEL%
)

echo Imagen publicada exitosamente.
