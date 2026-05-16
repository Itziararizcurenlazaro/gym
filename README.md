# OH MY GYM!

## Cómo poner en marcha el proyecto

Para ejecutar correctamente el proyecto es necesario tener instalado:

- IntelliJ IDEA
- JDK 25
- Apache Tomcat
- DBeaver con MariaDB vinculado

Pasos para iniciar la aplicación:

1. Descargar el archivo `.zip` del proyecto.
2. Cargar el proyecto en IntelliJ IDEA.
3. Realizar el *build* del proyecto desde IntelliJ.
4. Copiar las carpetas `dao`, `servlet` y `utils` generadas dentro de la carpeta `out` en la siguiente ruta:
``````
C:\apache-tomcat-11.0.21\apache-tomcat-11.0.21\webapps\gym\WEB-INF\classes
``````
5. Copiar los archivos HTML generados en `out` a la ruta:
``````
C:\apache-tomcat-11.0.21\apache-tomcat-11.0.21\webapps\gym
``````
6. Iniciar Apache Tomcat ejecutando el archivo `startup.bat`.
7. Acceder a la aplicación desde el navegador mediante la URL:

http://localhost:8080/gym

---

## Introducción y propósito de la aplicación

La aplicación web tiene como propósito gestionar la información de un gimnasio mediante una base de datos, permitiendo administrar usuarios, entrenadores y rutinas de entrenamiento.

---

## Descripción general del funcionamiento

La aplicación dispone de una interfaz gráfica que permite gestionar visualmente usuarios, entrenadores y rutinas.

La información se almacena de forma persistente en una base de datos MariaDB y se actualiza con cada acción realizada por el usuario. El sistema respeta las relaciones del modelo entidad-relación, garantizando la coherencia entre usuarios, entrenadores y rutinas.

---

## Tecnologías utilizadas

Para el desarrollo de la aplicación se han utilizado las siguientes tecnologías:

- Java como lenguaje principal de programación.
- MariaDB como sistema gestor de bases de datos relacional.
- JDBC para la conexión entre la aplicación Java y la base de datos.
- Apache Tomcat como servidor web y contenedor de servlets.
- Bootstrap para el diseño y maquetación de la interfaz web.
- IntelliJ IDEA como entorno de desarrollo.
- Git y GitHub para el control de versiones del proyecto.
