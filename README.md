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

Para tomar el resultado de `statement`, tenemos que ejecutar otro comando en el propio statement, llamado `getResulSet()`, este método devuelve un objeto de tipo ResulSet. ResulSet es un listado de resultado, y siempre que lo vayamos a leer tenemos que saber cuál es el próximo elemento desde la fila actual, o sea, mientras haya una fila en ese ResulSet nosotros podemos ir leyendo el resultado, cuando lleguemos al último ítem de este ResulSet el loop se termina. 

## PreparedStatement
El JDBC tiene una opción para validar las información de la query. En vez de crear y hacer uso de un `Statament`, vamos a preparar un `Statament`, y cuando hacemos eso, nosotros pasamos la responsabilidad de administrar los parámetros del comando SQL para el JDBC. 
El `PreparedStatement` mantiene la query compilada en la base de datos, de forma parametrizada. Así el usuario puede ejecutar la misma consulta diversas veces con parámetros distintos. 
