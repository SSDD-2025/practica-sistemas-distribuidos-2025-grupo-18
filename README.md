[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/D1C1HU9V)

**BOOKNEST**

Grupo 18: Guillermo Lara, Marta Sacristán, Ariabod Fatehi y Pablo León

**Participación de cada miembro del equipo:**
El trabajo ha sido repartido entre Guillermo y Marta prácticamente a parte iguales. Nos organizamos de forma que cada uno trabajaba sobre la práctica 2-3 días y después continuaba el otro, y así sucesivamente,para evitar complicaciones con Github. En cuanto al resto del grupo, su participación ha sido la de sus respectivos commits, que se mostrarán a continuación.

**Guillermo Lara**:

https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-18/commit/9922b908fcfb42a2ab65a37c75818161fa2c5a0a

https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-18/commit/ccc79b85ec9d0a54bc8f92f7289d285e86fb0f94 

https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-18/commit/cfe39ec39873293ae882dd7ad48253d6079e91ea 

https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-18/commit/cabab89bc0b44cf4bafd4bf569e55d9a35a3813f 

https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-18/commit/7933d8d1aeccb3c628edebcdca71c3db6eccb09f 

**Marta Sacristán**:

https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-18/commit/fd66814c4876a22dbba7bed78f7feef429f8aee0

https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-18/commit/b9386b16889e31a4b991a2c217aaa07cf965dc25

https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-18/commit/6b56055a6c5a2338c09f0a743e263c1a9d98e21c

https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-18/commit/75c8f90f8f93fed9dc99a124be32e8c74e8c3ae8

https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-18/commit/a4d8100f1805e3e26f1f561b316acfaf76ff2ed1


**Ariabod Fatehi**:

https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-18/commit/6ebe1c42efdecef22d2122730edf64b11d960987 

https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-18/commit/c1ba1bb7a486be6144a0d964cb2242daf6d798f0 

https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-18/commit/0e343e77268948ec28765d1a5952da04f77015ee 

https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-18/commit/d531a3b2a9f6cf33344a3d6a4dc1ba70ba4f2a9b 

https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-18/commit/14810a89c59395e7f3f012fc29cc4e5ca9c82d4a 


**Pablo León**:

https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-18/commit/363ab4caefad6bf1d10e9f31dd663df690140ace

https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-18/commit/7ca0e55d478ff410c96870f469ede96e39d4ede3 

https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-18/commit/34fcd0bbde27ad8c576cff9c4d923ba831ac9c36 

https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-18/commit/0a533ee1e4a0ac343d7bbe5dce8f76cbd54b71b0 

https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-18/commit/7be2f2a94127c912cc0a4e9f6f7f52d502404a81 

**Diagramas:**

**Diagrama de Entidad-Relación**

![image](https://github.com/user-attachments/assets/15535b36-3c5b-454b-8c40-ab7c76438149)



Este diagrama muestra las relaciones entre las entidades principales del sistema.

**Diagrama de Navegación y Páginas principales:**
La pagina de error(arriba izqda, la única sin conexiones visibles) es accesible desde cualquier sitio
![image](https://github.com/user-attachments/assets/08079230-41ec-4317-a083-26f935b82a10)




**Diagrama de Clases**

![BBDD](https://github.com/user-attachments/assets/df780b3d-118d-447d-98a5-4f8b6ffb2670)

![image](https://github.com/user-attachments/assets/b709809d-1f3a-47ae-acfa-22e3b60e3d42)






**Instrucciones de Ejecución:**

    Descarga el repositorio y descomprímelo.
    Ejecuta el proyecto en tu entorno de desarrollo de preferencia. Recomendamos VS Code.
    Cuando la aplicación esté en ejecución, abre tu navegador y ve a https://localhost:8443

    En lo que respecta a **Postman**, es necesario loggearse para algunas funcionalidad, y estas están limitadas en ocasiones a un único rol. 
    En caso de recibir un error 401 Unauthorized, hay que introducir en Authorization -> Bearer Token el token que nos da la response cuando inicias sesión con un usuario válido. Después, volver a ejecutar la petición que deseas realizar.
    Si da error 403 Forbidden, es porque ese rol de usuario no tiene permitido ejecutar esa acción.


**Instrucciones de Ejecución: aspectos a tener en cuenta respecto a las relaciones**

    - User: tenemos dos tipos (admin y user), además de una página inicial para un usuario no registrado con funcionalidad limitada.

    - Autor: se crea y puede estar asociado a libros o no.

    - Libro: un libro  contiene información como título, género, y fecha de publicación. Además, tiene asociado su autor. Un libro no se puede crear sin antes haber creado el autor de este (si es que no existe ya).

    - Reviews: pertenecen al tipo de usuario User (no admin). Todos pueden verlas, pero solo el propio usuario puede crear, modificar o eliminar sus propias reseñas.

Requisitos:
Java: JDK 21

Maven v0.44.0
En VS Code, puedes obtenerlo con la extensión Maven for Java.

Spring Boot 3.4.4
En VS Code, necesitarás la extensión Spring Boot Extension Pack.

