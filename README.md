# Drinkteam-webstore

### Introducción

- WebStore, es un sitio web el cual ofrece compra y venta de productos universitarios y subastas de los mismos en tiempo real de manera incógnita y segura, los datos de los usuarios nunca se verán comprometidos ni compartidos con otros usuarios. Esta aplicación busca que los productos universitarios sean accesibles para todos, sin necesidad de intermediarios, mitigando los posibles fraudes. la idea de esta aplicación es que los usuarios sean compradores o vendedores de sus propios productos creando anuncios (de forma directa con otro usuario o subastando) donde ellos coloquen el precio pero siempre estando en un margen lógico. Habrá un sistema de SCRUM donde se hará un depósito de garantía mientras se cumple el trato o acuerdo de la venta o compra del producto, la cual los usuarios de la transacción deben confirmen su visto bueno, esto asegurar que todos nuestros usuarios interactúen con la aplicación de manera confiable y sin riesgo alguno

### Conceptos generales

- SpringBoot: Es un Framework que nos permite crear aplicaciones web basados en anotaciones, la cual nos facilita realizar peticiones HTTP (REST), donde nos permite conectar el front-end con el back-end mediante una API.

- JSON: Formato de texto, donde nos permite el intercambio y el trato de datos entre la aplicación y nuestra API 

- Maven: Se usa para manejar el ciclo de vida del proyecto, donde usamos las dependencias que nos ofrece las cuales necesitamos para nuestro proyecto. como por ejemplo, SpringBoot, gson, etc.

- Heroku: Plataforma web que funciona como servidor, es basada en arquitectura Plataforma como servicio (PaaS) donde ofrece desarrollo y hospedaje a los usuarios. nos ofrece la posibilidad de subir nuestra aplicación web sin tener que especificar detalles del servidor, ni configuraciones avanzadas.

- Bootstrap: Framework para el desarrollo del front end de las aplicaciones web, nos permite usar las librerías (libres, es decir, open sources)  para la facilidad de crear el sitio web de forma dinámica, sencilla y moderna. Para que la experiencia del usuario sea agradable con nuestra aplicación

- Axios: Librería de JavaScript donde nos permite consumir la API mediante las respectivas peticiones (GET / POST / PUT…) es decir, es la conexión entre la aplicación web y la API

### Implementación

- En esta aplicación web al utilizar las peticiones REST usamos Spring el cual nos ofrece los métodos necesarios e interfaces para generar las peticiones necesarias para nuestra aplicación web; la conexión con la base de datos se hará por medio de base de datos No-SQL (mongoDB) donde se guardaran cada una de las entidades sin tener problemas en las relaciones o llaves. las entidades que usamos son Usuarios, Productos y transacciones (cada una tiene implementado su respectivo controlador, modelo, persistencia y servicios) donde se obtendrán sus verbos y recursos de manera eficiente, usando JSON para el trato de datos, esto generará una extensión de código o funcionalidades extras que puedan existir en un futuro.

En la interfaz gráfica utilizaremos bootstrap, usaremos para que el usuario tenga una experiencia amigable con nuestro sitio web, Y por último para la conexión entre los controladores de la API y el back end del aplicativo usaremos Axios, que permite una comunicación libre y sencilla trabajando  los datos en formato json ( haciendo peticiones REST)

### Alcance

- Esta aplicación web tiene como alcance la compra y venta de productos en tiempo real, donde los usuarios son anónimos, es decir, no se conocen los datos entre las partes. el registro de usuarios e inicio de sesión, se debe verificar que el correo electrónico no exista.

Usuarios ya registrados podrán consultar productos ya publicados, consultar estadísticas, ver perfil (editar perfil, con verificación de administrador), consultar confianza de usuarios, búsqueda por producto, filtro subastas,  crear anuncio de producto (venta directa o en subasta), hacer ofertas a subasta, consultar transacciones (Abiertass o finalizadas), darse de baja.

En las transacciones se tendrá el sistema SCRUM (depósito de garantía) donde ambas partes de la transacción deben verificar que todo esté bien y confirmar su finalidad de la venta.


### Extensibilidad

   TODO


### Diagramas

#### Diagrama de clases

![diagramaClases](https://user-images.githubusercontent.com/44879884/74988712-8c652100-540c-11ea-86bf-850ddb667c97.PNG)

#### Modelo E-R.

![WhatsApp Image 2020-02-20 at 4 17 07 PM](https://user-images.githubusercontent.com/44879884/74989071-72780e00-540d-11ea-9c1a-40f843d9c15e.jpeg)

#### Caso de uso

![caso](https://user-images.githubusercontent.com/44879884/74989135-99364480-540d-11ea-9d76-4ef2299c4e1f.PNG)

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

**Enlace:** [`project-arsw-webstore.herokuapp.com`](https://project-arsw-webstore.herokuapp.com/webstoreUser/users)

## DataBaseInfo
- Host: ec2-34-200-116-132.compute-1.amazonaws.com
- Database: dc5qsgdq0jgp20
- User: gpyoydzjumspiy
- Port: 5432
- Password: a92e5891a1f575b00c5319227c7f2acbadf68c4ef2dc9e1d35e76aab02c4a277
- URI: postgres://gpyoydzjumspiy:a92e5891a1f575b00c5319227c7f2acbadf68c4ef2dc9e1d35e76aab02c4a277@ec2-34-200-116-132.compute-1.amazonaws.com:5432/dc5qsgdq0jgp20
- Heroku CLI: heroku pg:psql postgresql-horizontal-84837 --app project-arsw-webstore

## Integrantes
  - Juan David Navarro Jimenez
  - Eduardo Ocampo Arellano
  - Juan Manuel Villate Isaza
