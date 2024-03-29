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

## Evitando SQL Injection utilizando PreparedStatement 
El JDBC tiene una opción para validar las información de la query. En vez de crear y hacer uso de un `Statament`, vamos a preparar un `Statament`, y cuando hacemos eso, nosotros pasamos la responsabilidad de administrar los parámetros del comando SQL para el JDBC. 
El `PreparedStatement` mantiene la query compilada en la base de datos, de forma parametrizada. Así el usuario puede ejecutar la misma consulta diversas veces con parámetros distintos. 

## Control de la transacción, commit y rollback 
- Las bases de datos ofrecen un recurso llamado **transacción**, que junta muchas operaciones SQL como un conjunto de ejecución;
    - Si el conjunto falla no es aplicada ninguna modificación y ocurre el *rollback* de la transacción.
    - Todos los comandos del conjunto necesitan funcionar para que la transacción sea finalizada con un *commit*.

## try-with-resources 
- Para garantizar el cierre de los recursos abiertos en el código, Java provee un recurso llamado *try-with-resources* para ayudarnos;
    - Para utilizar este recurso es necesario que la clase utilizada (como la `Connection`) implemente la interfaz `Autocloseable`.

## Pool de conexiones 
El pool de conexiones actúa como un grupo de conexiones reutilizables y listas para usar. Es una técnica que aumenta la eficiencia
y mejora el rendimiento en las aplicaciones que requieren acceso a recuros costosos, como las bases de datos.

El `DataSource` se asegura de que las conexiones se utilicen de manera eficiente y que no haya que estar abriendo y
cerrando conexiones cada vez que se necesita comunicarse con la BD. 

## Clase modelo (persistencia de los datos)
Se recomienda realizar una clase dentro del paquete `model` la cual contenga los mismos campos que la entidad o la tabla de la base de datos. 
En este aspecto se emplean los Getters y Setters dependiendo de la funcionalidad del programa. 

## DAO (Data Access Object) 
El DAO es un patrón de diseño. La finalidad de este patrón de diseño es tener un objeto que tiene como responsabilidad acceder a la BD o a cualquier fuente de datos para ejecutar las funciones necesarias. 

**¿Cuáles son las ventajas de utilizar clases con el estándar DAO?**  
Tiene que ver con la capacidad de aislar en un lugar centralizado, toda la lógica de acceso al repositorio de datos de la entidad. 

## El estándar MVC
Las capas más conocidas son las de view, controller, modelo y persistencia, que componen el estándar MVC. 
