# x-men

Proyecto que permite exponer dos apis, una que segun el ADN enviado identifica si es de un mutante o un humano y otra que devuelve las estadisticas de los ADN's verificados

### Pre-requisitos 📋

_Java 11_

_Mysql_

## Comenzando 🚀

Crear en Mysql una base de datos con el nombre xmendb.

Clonar el repositorio, abrir la aplicacion en un IDE(Eclipse, Netbeans, Intellij, STS, etc), ir al archivo DataSourceConfig.java ubicado en la ruta /src/main/java/co/com/mercadolibre/xmen/configuration/DataSourceConfig.java y validar la información de su usuario y contraseña de MySQL y finalmente ejecutar la aplicacion.

### Firma de las apis 📋

La aplicacion utiliza las librerias de Swagger para generar la firma de las apis, para acceder a la interfaz se debe ejecutar la aplicación en el entorno local e ingresar al link http://localhost:8080/swagger-ui.html

![Alt text](/src/main/resources/static/api-mutant.png?raw=true "Optional Title")

![Alt text](/src/main/resources/static/api-stats.png?raw=true "Optional Title")

### Instrucciones consumo de las apis 📋

Descargar el archivo de la siguiente ruta e importarlo en la aplicacion de postman

```
/src/main/resources/static/Xmen.postman_collection.json
```

## Ejecutando las pruebas ⚙️

Se utilizo Junit para construir cada una de las pruebas unitarias y se configuro el pluggin de jacoco para generar el informe de cobertura de las pruebas.

_Para correr las pruebas y generar el reporte de cobertura se debe ejecutar el siguiente comando._

```
gradlew clean build jacocoTestReport
```

Luego buscar el archivo con el resultado del informe en la ruta 

```
/build/jacocoHtml/index.html
```

![Alt text](/src/main/resources/static/reporte-pruebas.png?raw=true "Optional Title")

Para algunas de las diferentes pruebas se utilizo el set de datos:

![Alt text](/src/main/resources/static/casos-prueba.png?raw=true "Optional Title")

## Despliegue 📦

_Se despliega la aplicación en una instancia de EC2 de AWS y se configura un servicio que ejecuta el .jar automáticamente cuando la instancia se inicia_

## Construido con 🛠️

_La aplicación fue construida con el lenguaje de programacion java, gradle como gestor de dependencias, springboot como framework y para la base de datos se utilizo MySQL_

---
Hecho con ❤️ por [sebastianuribea](https://github.com/sebastianuribea) 😊
