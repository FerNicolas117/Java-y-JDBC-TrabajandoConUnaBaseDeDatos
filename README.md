# Java y JDBC: Trabajando con una Base de Datos
Implementación de un CRUD empleando Java Data Base Conectivity (JDBC) en un proyecto Maven de Java. Se trabaja con MySQL, con una tabla 'producto' en donde existen las columnas:
- id (INT) -> autoincrementable
- nombre (VARCHAR)
- descripcion (VARCHAR)
- cantidad (INT)

## Statament
El comando `SELECT` es considerado como Statement en Java. Para crear una Statement debemos de utiliza la conexión a la base de datos, y hay que ejecutar un comando llamado `createStatement()` que devuelve un objeto del mismo tipo (statement). 

Así, con ese objeto, podemos crear la query SQL, usando el método del statement llamando `execute` y en su parámetro colocamos la query. 

El `execute` devuelve un booleano, por qué? Esto es para indicar acerca del resultado de statement que nosotros creamos, nos indica si el resultado es un listado o no, si es un listado devuelve un `True`, pero si estamos ejecutando un INSERT, UPDATE o un DELETE es resultado no es un listado, entonces devuelve el resultado del booleano como un `false`.
