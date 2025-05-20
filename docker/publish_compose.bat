@echo off
set DOCKER_USER=guillermolara2004
set TAG=booknest-compose:1.0.0

docker compose --file docker-compose.prod.yml publish guillermolara2004/booknest-compose:1.0.0 --with-env
