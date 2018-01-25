# Examen Mercadolibre 

## Introduccion
Este proyecto es un desarrollo realizado como parte del proceso de selección de personal de [Mercadolibre](http://mercadolibre.com).

Para dar con la solucion requerida y teniendo en cuenta los requerimientos tantos funcionales como los no funcionales, se desarrollaron tres aplicaciónes:

  - **MeLi Gateway**: Api Gateway de la solución
  - **Meli Mutant**: Aplicación responsable de processar los ADN ingresados y determinar si la persona es mutante o no.
  - **Meli Stats**: Aplicación responseble de las estadísticas.  



## Instalación 
###  Dependencias
Para poder probar esta solución en un entorno local deberá tener instaladas las siguientes dependencias:

  - Java 8 https://java.com/es/download/
  - Maven https://maven.apache.org/install.html
  - MongoDB https://docs.mongodb.com/manual/installation/
  - MySql  https://dev.mysql.com/doc/refman/5.7/en/binary-installation.html
  - Rabbitmq  https://www.rabbitmq.com/download.html
  
 ### Properties 
Dentro de cada uno de las aplicaciones bajo la ruta src/main/resources podra encontrar un archivo **application.properties** que contiene configuracion de base. 

>  Puede agregar su propio properties creando, por ejemplo, appliaction-local.properties modificando las variables que necesite. Al momento de correr la aplicacion debera determinar el entorno para asi tomar sus variables.Más documentación     [aquí](https://docs.spring.io/spring-boot/docs/current/reference/html/howto-properties-and-configuration.html).

### Probar Aplicaciónes
Para poder probar cada una de las aplicaciones debera ingresar a cada uno de los directorios de las distintas applicaciones ejecutar 

```sh
$ mvn spring-boot:run
```

Por ejemplo:
```sh
$ cd MeLi-Gateway
$ mvn spring-boot:run
```
Luego de ejecutar podra consumir la api apuntando a http://localhost:8888. 

| VERBO | URL                    |
|-------| -----------------------| 
| GET   | localhost:8888/stats   |
| POST  | localhost:8888/mutant/ |

### Demo
Para poder probar la aplicación sin necesidad de descargar el código fuente puede hacerlo desde: http://35.229.70.0:8888

Ejemplos en curl:

$ curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://35.229.70.0:8888/stats

## Mejoras
Existen infinidad de mejoras que se pueden realizar para dar con una mejor solucion, algunas desde el código y otros desde la arquitectura pensada.

Algunas de esas mejoras pueden ser:
-   Utilizar **Docker** para el deploy de cada una de estas aplicaciones.
-   Ahgregar **Config Server**, ligado a un repositorio Git, que permite centralizar  la configuración de todos los microservicios. 
-   Service registry como por ejemplo **Eureka Discovery**, para regstrar y despues estionar las distintas instancias de esos microservicios.
-   Reemplazar el Gateway actual por un **API Gateway** como Zuul es un punto de entrada de todas las peticiones, 


## Aclaraciones

Para el caso de la persistencia de las estadisticas, que se generar al momento de procesar los ADNs. Estas se agrupan por fecha ya que podría solicitarse diferentes agrupamientos. Por ejemplo, mes, dia o año. Y en tal caso podriamos hacerlo con solo agregar un nuevo endpoint o modificar el actual.

**MUCHAS GRACIAS!**

