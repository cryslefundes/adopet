<h1 align="center" style="font-weight: bold;">Adopet 🐾</h1>

<p align="center">
    <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white"  alt="Java badge"/>
    <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" alt="Spring badge">
    <img src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white" alt="Docker badge">
    <img src="https://img.shields.io/badge/rabbitmq-%23FF6600.svg?&style=for-the-badge&logo=rabbitmq&logoColor=white" alt="RabbitMQ badge">
    <img src="https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white" alt="Maven badge">
    <img src="https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white" alt="Postgres badge">
</p>

<p align="center">
 <a href="#started">Getting Started</a> • 
  <a href="#routes">API Endpoints</a> •
 <a href="#colab">Collaborators</a> •
 <a href="#contribute">Contribute</a>
</p>

<p align="center">
  <b>API developed with the goal of practice Spring Web, unit tests with Mockito and RabbitMQ.</b>
</p>

<h2 id="started">🚀 Getting started</h2>

To run this project, you need to execute the container with PostgreSQL and RabbitMQ, adjust the environment
variables according to the configurations you've redefined in both `application.properties` and `compose.yaml`. 
By doing so, you will be ready to run the API normally.

<h3>Prerequisites</h3>

Here you list all prerequisites necessary for running your project. For example:

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Docker](https://docs.docker.com/get-docker/)
- [Spring 3.2.3](https://spring.io)
- [Open API 2.4.0](https://springdoc.org)

Spring dependencies will be found in `pom.xml` file in the root of the project.

<h3>Cloning</h3>

To clone this repository, you need to execute this bash command:

```bash
git clone https://github.com/CrysLef/adopet.git
```

<h3> Environment Variables</h3>

There are the enviroment variables in the `application.properties` file, they have default values, if your credentials
are different, please change this:

```properties
spring.datasource.url=jdbc:postgresql://${DB_HOST:localhost}:15432/${DB_NAME:adopet}?createDatabaseIfNotExist=true
spring.datasource.username=${DB_USER:postgres}
spring.datasource.password=${DB_PASSWORD:admin}

spring.rabbitmq.host=${RABBITMQ_HOST:localhost}
spring.rabbitmq.username=${RABBITMQ_USER:guest}
spring.rabbitmq.password=${RABBITMQ_PASSWORD:guest}
```

Don't forget to change this environment variables in the `compose.yaml` file too:

```yaml
environment:
  POSTGRES_DB: "adopet"
  POSTGRES_PASSWORD: "admin"
```

<h3>Starting</h3>

To start the project, it is necessary run your database and rabbitmq configured first, for that, you just need to execute this command:

```bash
docker compose up -d 
``````
Doing that, to access rabbitmq management, go to `localhost:15672` and enter with your credentials
or with the default value: `username: guest` and `password: guest`. With that, you'll be capable to
create your queue named `email`, after creating the queue, just send requests to the under endpoints
in `localhost:8080/endpoint-name`. For consume this produced emails, you'll need clone another project
of mine or create your own based on that, but the idea here is doing a "microservice" with separeted repos,
 if you want clone, run this:
```bash
git clone https://github.com/CrysLef/EmailHandler.git 
```
Follow the instructions in the [repo](https://github.com/CrysLef/EmailHandler), run `docker compose up -d` there and request with a available email to this work. 

<h2 id="routes">📍 API Endpoints</h2>

Here you will find all the endpoints, HTTP methods accepted and their descriptions. If want
test this endpoints and more info about it, access the api doc in `localhost:8080/swagger-ui.html` 
when you execute the `compose.yaml`.

<h3>Shelters</h3>

| route                         | description                                                   |
|-------------------------------|---------------------------------------------------------------|
| <kbd>GET /shelters</kbd>      | Retrieves a page containing 10 shelters each sorting by name. |
| <kbd>GET /shelters/{id}</kbd> | Retrieves a shelter according to id in path variable.         |
| <kbd>POST /shelters</kbd>     | Register a shelter.                                           |
| <kbd>PUT /shelters</kbd>      | Update a shelter.                                             |


<h3>Tutors</h3>

| route                       | description                                                 |
|-----------------------------|-------------------------------------------------------------|
| <kbd>GET /tutors</kbd>      | Retrieves a page containing 10 tutors each sorting by name. |
| <kbd>GET /tutors/{id}</kbd> | Retrieves a tutor according to id in path variable.         |
| <kbd>POST /tutors</kbd>     | Register a tutor.                                           |
| <kbd>PUT /tutors</kbd>      | Update a tutor.                                             |

<h3>Pets</h3>

| route                     | description                                               |
|---------------------------|-----------------------------------------------------------|
| <kbd>GET /pets</kbd>      | Retrieves a page containing 10 pets each sorting by name. |
| <kbd>GET /pets/{id}</kbd> | Retrieves a pet according to id in path variable.         |
| <kbd>POST /pets</kbd>     | Register a pet.                                           |
| <kbd>PUT /pets</kbd>      | Update a pet.                                             |

<h3>Adoptions</h3>

| route                          | description                                                    |
|--------------------------------|----------------------------------------------------------------|
| <kbd>POST /adoptions</kbd>     | Retrieves a page containing 10 adoptions each sorting by name. |
| <kbd>PUT /adoptions/{id}</kbd> | Retrieves  a tutor according to id in path variable.           |
| <kbd>PUT /tutors</kbd>         | Update a adoption.                                             |

<h2 id="contribute">📫 Contribute</h2>

To contribute, you be able to use git flow, you just need to follow this steps:

1. `git clone https://github.com/CrysLef/adopet.git`
2. `git flow feature start FEATURE_NAME`
3. Follow commit patterns using `feat: ` when you want to add a feature in the project
4. Finished the feature, you just need to execute this command: `git flow feature finish FEATURE_NAME`
5. Open a Pull Request explaining the problem solved or feature made, if exists, append screenshot of visual modifications and wait for the review!

<h3>Documentations that might help</h3>

[📝 How to create a Pull Request](https://www.atlassian.com/br/git/tutorials/making-a-pull-request)

[💾 Commit pattern](https://gist.github.com/joshbuchea/6f47e86d2510bce28f8e7f42ae84c716)

<h2 id="contribute">📚 References</h2>

- [Alura Course](https://github.com/alura-cursos/3349-boas-praticas-java-testes)
- [Michelli Brito](https://github.com/MichelliBrito/microservices-na-pratica/tree/main)
- [Marcos's Medium](https://mmarcosab.medium.com/tutorial-rabbitmq-com-spring-boot-480e3a6682e6)

