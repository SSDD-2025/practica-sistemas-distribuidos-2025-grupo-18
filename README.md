11/04
Cosas que faltan por hacer:




-Poder modificar tus datos, añadir listas de libros
-Añadir imagenes en modificar imagenes y en añadir libro
-Limpiar codigo y poner las restricciones necesarias en los controladores y no en los html





**COSAS QUE HE HECHO:**

    1.- He creado una pantalla inicial (similar a Letterboxd) donde se muestran los libros, modificando y creando tanto HTMLs como el código Java (sobre todo los controllers para hacer bien los Mapping). Con esto, considero que la página para "usuario no registrado" ya estaría completa, ya que solo puede ver los libros y no puede hacer nada más.

    2.- Además, he añadido navegación por la página de 2 formas (de momento):

        2.1.- Si pulsas en el libro de la página principal, nos lleva a la página de detalles del libro.

        2.2.- Dentro de los detalles del libro, aparece el autor. Si pulsamos encima de su nombre, vamos a ir a una página de detalles del autor, que nos muestra su biografía y los libros existentes en la página que sean suyos.

        ¡!: de momento, los libros y autores se inicializan en SampleData, con la función init.

    3.- Si pulsamos en Log In, nos va a llevar a una página de "admin", donde deberíamos d epoder gestionar libros y autores. De momento, no funciona, pero ya he creado la estructura de la página y el controller que nos servirán de base.


**COSAS QUE HAY QUE HACER:**

    1.- Mi idea sería hacer primero la parte de la Fase 2 (seguridad), porque necesitamos los distintos roles (user y admin) para añadir la funcionalidad de cada uno:

        - User: crear listas de libros y añadir reseñas. La página de "usuario registrado" debería de ser muy similar a la de no registrado, con la diferencia de esas 2 funciones cuando pulsemos en los detalles de un libro. Además, en la cabecera debería de tener la opción de configurar su cuenta (lo dijo el profe en clase cuando explicó la rúbrica).

        - Admin: gestionar (ver, modificar y borrar) libros y autores. Esta página no tiene tanta importancia, simplemente tiene que poder gestionarlo de manera efectiva en la base de datos y tenemos que asegurarnos de que no nos deje meternos de cualquier manera (seguridad).


Respecto a la estructura, Como podéis ver, he modificado prácticamente todo el trabajo. 
Además, teníamos otro repositorio en el que he hecho todos estos cambios para no modificar el repositorio de la práctica 1 mientras el profe estaba corrigiendo. Así que, **IMPORTANTE**, si descomentáis alguna clase que está comentada, es posible que os salgan errores en los imports y en el paquete. Tendréis que cambiar de: "package es.codeurjc.booknest.controller;" a "package es.codeurjc.trabajoweb_vscode.controller;" y lo mismo con el resto de imports que puedan dar error. 

Al ejecutar el trabajo con Spring Boot, os van a salir 2 opciones: seleccionar la de BooknestApplication.


------------------------------------------------------------------------------------------------------
10/3 --> Guille

Los cambios que he hecho en general han sido de simplicar y poner un poco todo en orden, he estado pensando como organizar todo y que estructura debemos de seguir. En conclusión pienso que lo mejor es seguir la estructura de url del mismo letterbox.

Objetivos:

Crear la pantalla principal para un usuario que no está registrado Configurar el login Adaptar la pantlla principal para que haya diferencias cuando se ha iniciado sesion