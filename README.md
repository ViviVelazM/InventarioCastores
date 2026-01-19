# InventarioCastores

- IDE utilizado: NetBeans v27
- Lenguaje utilizado: Java 21.0.9
- DBMS utilizado: MySQL 8.0.44
- Servidor web: TomCat 10.1.50

Dependencias:\n
El proyecto cuenta con el uso de las librerías: *Esto en caso de que al importar el proyecto no sean detectadas por cualquier inconveniente*
- LIBS/jakarta.servlet.jsp.jstl-3.0.1.jar
- LIBS/jakarta.servlet.jsp.jstl-api-3.0.0.jar
- LIBS/mysql-connector-j-9.5.0.jar


Lista de pasos para ejecución:
- Es necesario contar con la base de datos ya creada correctamente, ejecutando los scripts "Creación" y "Llenado".
- Se debe considerar el Servidor web TomCat
- Dentro del proyecto se debe de considerar modificar el usuario y contraseña para la conexión a la BD, esto dentro del archivo "Conexion.java", que está en la ruta "Castores/src/java/config/Conexion.java".
- Teniendo abierto el proyecto en el IDE, se ejecuta, este lanza la aplicación por medio de TomCat.
- Al ejecutarlo este mandará a index.html, el cual por si mismo redirigirá hacia la página correspondiente de InventarioController que llevará al login.
- Una vez ejecutado cuando menos una vez desde el IDE, puede accederse desde navegador directamente con la URL "http://localhost:8080/Castores/InventarioController"
- Para el acceso de usuarios, se pueden consultar los diferentes usuarios que se han llenado en la base de datos. Unos de ellos son los siguientes:
- - Rol de Administrador, usuario: vivi.velaz@gmail.com, contraseña:admin123
  - Rol de Almacenista, usuario: anto.perez@gmail.com, contraseña: alma123
