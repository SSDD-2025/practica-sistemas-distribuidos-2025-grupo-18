# Booknest - Proyecto Dockerizado

## Ejecución de la Aplicación Dockerizada

### Requisitos

- Docker (`>= 20.10`)
- Docker Compose (`>= 1.29`)
- Acceso a internet para descargar imágenes


### Modo Desarrollo (con base de datos local)

```bash
docker compose -f [name] up
```

* App disponible en: [https://localhost:8443](https://localhost:8443)
* Base de datos expuesta en el puerto `3307`


## Construcción y Publicación de la Imagen Docker

### Requisitos

* Maven
* Docker
* Cuenta en DockerHub


### Construir la imagen Docker

```bash
./docker/create_image.bat 
```


### Subir la imagen a DockerHub

```bash
./docker/publish_image.bat 
```

## Publicación de `docker-compose.prod.yml` como OCI Artifact

```bash
./publish_compose.bat
```

Este comando publicará el archivo `docker-compose.prod.yml` como un artefacto OCI en DockerHub usando:

```bash
docker compose --file docker-compose.prod.yml publish guillermolara2004/booknest-compose:1.0.0
```


## Despliegue en Máquinas Virtuales (Universidad)

### Requisitos

* Acceso SSH a las VMs
* Docker instalado
* Clave privada `sidi18.key`


### Restaurar permisos de clave SSH en Windows (PowerShell)

```powershell
icacls "ssh-keys\sidi18.key" /inheritance:r
icacls "ssh-keys\sidi18.key" /remove "BUILTIN\Usuarios"
icacls "ssh-keys\sidi18.key" /grant:r "$($env:USERNAME):R"
```



### Acceder a las VMs

1. Acceder a la primera VM:

```bash
ssh -i ssh-keys/sidi18.key vmuser@193.147.60.58
```

2. Desde allí, conectarse a la segunda:

```bash
ssh -t -i ssh-keys/sidi18.key vmuser@193.147.60.58 ssh sidi18-2
```



### Descargar imagen WEB

```bash
sudo docker pull guillermolara2004/booknest:1.0.0
```


### Lanzar base de datos

```bash
sudo docker run -d --name mysql-db \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=booknest \
  -v db_data:/var/lib/mysql \
  -p 3306:3306 \
  mysql:9.2
```



### Lanzar aplicación web

```bash
sudo docker run -d \
  --name booknest-web \
  -p 8443:8443 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e DB_HOST=192.168.110.121 \
  -e DB_PORT=3306 \
  -e DB_NAME=booknest \
  -e DB_USER=root \
  -e DB_PASSWORD=root \
  guillermolara2004/booknest:1.0.0
```


## Comandos útiles en las VMs

#### Borrar contenedores

```bash
sudo docker rm -f booknest-web
sudo docker rm -f mysql-db
```

#### Ver contenedores existentes

```bash
sudo docker ps -a
```

### Administrar la base de datos MySQL

```bash
sudo docker exec -it mysql-db mysql -u root -p
```

Dentro de MySQL:

```sql
SHOW DATABASES;
DROP DATABASE booknest;
CREATE DATABASE booknest;
```

## URL de la Aplicación en Producción

> [https://193.147.60.58:8443](https://193.147.60.58:8443)


## Credenciales de Usuarios de Ejemplo

| Rol           | Nombre  | Contraseña |
|---------------|---------|------------|
| Administrador | admin   | admin      |
| Usuario       | user    | pass       |
| Usuario       | user2   | pass2      |

> Estos usuarios solo estarán disponibles si se carga `SampleData.java`
