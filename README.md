[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/D1C1HU9V)

Grupo: 18 (Ariabod Fatehi, Marta Sancristán, GUillermo Lara y Pablo León) 
Nota:
1.	Se ha rellenado la rúbrica antes de la defensa
Si 

Funcionalidad de la aplicación

2.	Funcionalidad completa (Creación, consulta y borrado de todas las entidades)
Completa
3.	Se puede editar al menos una entidad 
Si 
4.	Al menos una entidad tiene una imagen como parámetro, se puede visualizar y se almacena en BBDD
Si 
5.	La aplicación tiene datos de ejemplo cargados
Si 
6.	Se utiliza una BBDD MySQL
Si 
7.	Al menos  en una entidad se valida un campo en su creación en el formulario 
Si 
8.	Cualquier tipo de cambio sobre un registro se refleja en la BBDD
Si 
9.	Existen relaciones entre entidades en la BBDD
Si 
10.	La aplicación sigue una arquitectura adecuada (Controlador/Servicio/Repositorio)
Si
11.	Contiene estilos (CSS)
 Algunos elementos sin estilos (-0.5) 
12.	La aplicación tiene errores 
No
13.	Hay páginas de error propias
No (-0.5)



Calidad del código

14.	El código y los comentarios están en inglés
Si 
15.	El código tiene un formato y estilo adecuado.
Si 

Documentación y repositorio GitHub

16.	La documentación contiene un diagrama de navegación 
Si 
17.	La documentación contiene un diagrama con las entidades de la base de datos actualizado
Si 
18.	La documentación contiene un diagrama con las clases separando los controladores, servicios, repositorios, etc.
Si 
19.	La documentación contiene una sección con instrucciones sobre cómo configurar el entorno de desarrollo y cómo desarrollar la aplicación
Si
20.	La documentación contiene información sobre la participación de cada miembro del equipo
Si 
21.	Uso de GitHub
Poco uso (-1)

Participación de cada miembro del equipo:
El desarrollo del proyecto se hizo en local, los commits en github aunque sean subidos por una persona, tienen la participación de todos los miembros del equipo, 

Este es el diagrama de clases:

![image](https://github.com/user-attachments/assets/e2ad7d6a-6b73-40a4-b405-4a62b87dc711)

Este es el diagrama de navegación:
![image](https://github.com/user-attachments/assets/086371fe-d249-400b-89e2-690718cddd4d)

Con las pantallas mas significativas:
![image](https://github.com/user-attachments/assets/6dcf802b-4dc7-4ed9-8da4-59f5c6ee6530)


Este es el diagrama con las entidades de la base de datos:
![image](https://github.com/user-attachments/assets/995711c8-406d-4369-844c-b38c68e6a842)

documentación de las instrucciones para el entorno de desarrollo:
Solo hemos usado un plugin, el spring boot extension pack:

las dependencias son estas:
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-mustache</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
   
   <dependency>
     <groupId>org.apache.commons</groupId>
     <artifactId>commons-math3</artifactId>
     <version>3.6.1</version>
   </dependency>
 </dependencies>








