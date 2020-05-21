# Drinkteam - IncogniTrade - ARSW 

## Integrantes
  - Juan David Navarro Jimenez
  - Eduardo Ocampo Arellano
  - Juan Manuel Villate Isaza
  
### Usuario de sustentación

UserId: sebastian

password: sebastian

### Introducción

-  IncogniTrade, es un sitio web que ofrece compra y venta de todo tipo de productos y subastas de los mismos en tiempo real de manera incógnita y segura, los datos de los usuarios nunca se verán comprometidos ni compartidos con otros usuarios. Esta aplicación busca que los productos sean accesibles para todos, sin necesidad de intermediarios, mitigando los posibles fraudes. la idea de esta aplicación es que los usuarios sean compradores o vendedores de sus propios productos creando anuncios (de forma directa con otro usuario o subastando) donde ellos coloquen el precio pero siempre estando en un margen lógico. Habrá un sistema de SCRUM donde se hará un depósito de garantía mientras se cumple el trato o acuerdo de la venta o compra del producto, la cual los usuarios de la transacción deben confirmar y dar su visto bueno, esto asegurará que todos nuestros usuarios interactúen con la aplicación de manera confiable y sin riesgo alguno

### Atributos no funcionales 

#### Usabilidad

La usabilidad es uno de los dos escenarios a los que apuntamos con nuestra aplicación, basados en las 10 heuristicas de usabilidad de Jakob Nielsen. Para poderlas cumplir mantenemos informado al usuario de los procesos dentro de la aplicación brindando solo la información que es relevante para ellos, ademas la aplicacion da libertad y facilidad de navegacion al ser sencilla e intuitiva. Para realizar los procesos el usuario no tiene necesidad de acceder a paginas diferentes para encontrar informacion adicional, esta informacion siempre la brinda la pagina. La aplicacion hace manejo de errores mediante excepciones, comunicando al usuario en caso de que se presente un error fatal.

#### Rendimiento

El rendimiento fue el segundo escenario al que apuntamos en nuestra aplicacion. Para poder dar un mejor rendimiento se utiliza la memoria cache, que nos permite no tener que consultar la información de la base de datos cada vez que se requiere, sino recurrir al cache para agilizar el proceso de obtención de informacion. La aplicacion primero revisa si la informacion que necesita puede sacarla de la memoria cache y en caso de que no la encuentre, recurre a la base de datos y actualiza el cache para futuras ocasiones.

En la siguiente imagen se puede ver cuanto se demora en cargar la pantalla de Dashboard cuando la informacion no está en cache

![NoCache](https://user-images.githubusercontent.com/53972469/82526510-d50aa400-9af9-11ea-8fd8-66e254b3dc4e.png)

Al haber ya cargado esta informacion, se encuentra en cache y al volver a recargarse se demora mucho menos.

![Cache](https://user-images.githubusercontent.com/53972469/82526599-02efe880-9afa-11ea-8620-b4ec6dfed421.png)

Esta consulta es una de las mas pesadas al necesitar cargar mucha informacion en una misma pagina, demorandose 1355ms cargando sin cache y 89ms con cache.

### Instrucciones de uso

Nuestra aplicacion es muy sencilla de utilizar, al dirigirse al link del despliegue de Heroku en la parte inferior de este repositorio, será dirigido a la siguiente pagina. 

![Index](https://user-images.githubusercontent.com/53972469/82520219-3b3afb00-9ae9-11ea-95b5-ae5ed1367544.png)

Acá puede registrarse poniendo click en el boton Sign Up Now! Este boton lo dirigirá al siguiente formulario donde necesita llenar la información para luego poder entrar con su usuario respectivo.

![SignUp](https://user-images.githubusercontent.com/53972469/82520266-59086000-9ae9-11ea-8ab4-f19e4357953a.png)

Despues de registrarse, puede loguearse llenando los siguientes campos.

![Login](https://user-images.githubusercontent.com/53972469/82520305-7806f200-9ae9-11ea-8149-5da91b84ae6a.png)

Al entrar, la pagina lo llevara a su perfil donde podrá ver los productos que ya ha publicado y la opcion de revisar sus subastas, transacciones en proceso e historial de compras. En esta pagina tambien se pueden agregar productos nuevos para poner a la venta, agregar creditos dentro de la cuenta y editar la informacion del perfil. 

![profile](https://user-images.githubusercontent.com/53972469/82520412-b56b7f80-9ae9-11ea-8508-3a703655c626.png)

Al dirigirse al boton de Dashboard podrá ver todos los productos que se estan vendiendo al igual que las subastas en curso.

![Dashboard](https://user-images.githubusercontent.com/53972469/82477988-d4d8bd00-9a95-11ea-8bdb-607bc1c34cfc.png)

### Conceptos generales

- SpringBoot: Es un Framework que nos permite crear aplicaciones web basados en anotaciones, la cual nos facilita realizar peticiones HTTP (REST), donde nos permite conectar el front-end con el back-end mediante una API.

- JSON: Formato de texto, donde nos permite el intercambio y el trato de datos entre la aplicación y nuestra API 

- Maven: Se usa para manejar el ciclo de vida del proyecto, donde usamos las dependencias que nos ofrece las cuales necesitamos para nuestro proyecto. como por ejemplo, SpringBoot, gson, etc.

- Heroku: Plataforma web que funciona como servidor, es basada en arquitectura Plataforma como servicio (PaaS) donde ofrece desarrollo y hospedaje a los usuarios. nos ofrece la posibilidad de subir nuestra aplicación web sin tener que especificar detalles del servidor, ni configuraciones avanzadas.

- Bootstrap: Framework para el desarrollo del front end de las aplicaciones web, nos permite usar las librerías (libres, es decir, open sources)  para la facilidad de crear el sitio web de forma dinámica, sencilla y moderna. Para que la experiencia del usuario sea agradable con nuestra aplicación

- Axios: Librería de JavaScript donde nos permite consumir la API mediante las respectivas peticiones (GET / POST / PUT…) es decir, es la conexión entre la aplicación web y la API

### Implementación

-  En esta aplicación web al utilizará las peticiones REST usando Spring, el cual nos ofrece los métodos necesarios e interfaces para generar las peticiones necesarias para nuestra aplicación web; la conexión con la base de datos se hará por medio de base de datos PotsgreSQL montada en Heroku donde se guardaran cada una de las entidades sin tener problemas en las relaciones o llaves. Las entidades que usamos son Usuarios, Productos, Transacciones y Subasta (cada una tiene implementado su respectivo controlador, modelo, persistencia y servicios) permitiendo así la extensivildad del codigo. Mediante este modelo se obtendrán sus verbos y recursos de manera eficiente, usando JSON para el trato de datos, esto generará una extensión de código o funcionalidades extras que puedan existir en un futuro.

En la interfaz gráfica utilizaremos bootstrap, lo usaremos para que el usuario tenga una experiencia amigable con nuestro sitio web, Y por último para la conexión entre los controladores de la API y el back end del aplicativo usaremos Axios, que permite una comunicación libre y sencilla trabajando  los datos en formato json (haciendo peticiones REST).

### Alcance

- Esta aplicación web tiene como alcance la compra y venta de productos en tiempo real, donde los usuarios son anónimos, es decir, no se conocen los datos entre las partes. el registro de usuarios e inicio de sesión, se debe verificar que el correo electrónico no exista.

Usuarios ya registrados podrán consultar productos ya publicados, consultar estadísticas, ver perfil (editar perfil, con verificación de administrador), consultar confianza de usuarios, búsqueda por producto, filtro subastas,  crear anuncio de producto (venta directa o en subasta), hacer ofertas a subasta, consultar transacciones (Abiertass o finalizadas), darse de baja.

En las transacciones se tendrá el sistema SCRUM (depósito de garantía) donde ambas partes de la transacción deben verificar que todo esté bien y confirmar su finalidad de la venta.


### Extensibilidad

   Se diseñó y organizo el codigo para facilitar la extensibilidad mediante la utilizacion de diferentes capas como lo son: cache, controladores, modelo, persistencia y servicios. Esto permite tener los elementos desacoplados permitiendo tener un codigo mucho mas extendible para posibles funcionalidades futuras que se pretendan implementar. 


### Diagramas

#### Diagrama de clases

![Clases](https://user-images.githubusercontent.com/53972469/81290140-282a2480-902d-11ea-9491-fb3c1fc0d5cb.png)

#### Modelo E-R.

![DB](https://user-images.githubusercontent.com/53972469/82473577-65f86580-9a8f-11ea-8ceb-84f1f6de4c34.png)

#### Caso de uso

![Casos](https://user-images.githubusercontent.com/53972469/82505047-7544d680-9ac2-11ea-8111-5486ae5cfeee.png)

### Diagrama de componentes

![Componentes](https://user-images.githubusercontent.com/53972469/82508131-bc829580-9ac9-11ea-8937-d75ec62a1ad5.png)

### Diagrama de despliegue

![Despliegue](https://user-images.githubusercontent.com/53972469/80711621-47b5d000-8ab6-11ea-94e2-56a2c68d2815.png)

#### Mockup

![Capture](https://user-images.githubusercontent.com/53972469/75044637-fd99e800-548f-11ea-8c26-4d4f74d427ce.PNG)

## Taiga
**Enlace:**[Taiga](https://tree.taiga.io/project/villate13-drinkteam-arsw/timeline)

## Despliegue continuo

[![CircleCI](https://circleci.com/gh/DrinkTeam-Project-Arsw/drinkteam-webstore.svg?style=svg)](https://circleci.com/gh/DrinkTeam-Project-Arsw/drinkteam-webstore)

## Codacy

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/ad2af9350d84404d80b3aee9064be5fe)](https://www.codacy.com/gh/DrinkTeam-Project-Arsw/drinkteam-webstore?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=DrinkTeam-Project-Arsw/drinkteam-webstore&amp;utm_campaign=Badge_Grade)

## Heroku

[![Deployed to Heroku](https://www.herokucdn.com/deploy/button.png)](https://project-arsw-webstore.herokuapp.com/index.html)

**Enlace:** [`project-arsw-webstore.herokuapp.com`](https://project-arsw-webstore.herokuapp.com)


