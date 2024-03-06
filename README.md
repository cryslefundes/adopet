[JAVA_BADGE]:https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[SPRING_BADGE]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[POSTGRES_BADGE]:https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white
[DOCKER_BADGE]:https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white
[MAVEN_BADGE]:https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white

<h1 align="center" style="font-weight: bold;">Adopet 🐾</h1>

![spring][SPRING_BADGE]
![java][JAVA_BADGE]
![postgres][POSTGRES_BADGE]
![docker][DOCKER_BADGE]
![maven][MAVEN_BADGE]

<p align="center">
 <a href="#started">Getting Started</a> • 
  <a href="#routes">API Endpoints</a> •
 <a href="#colab">Collaborators</a> •
 <a href="#contribute">Contribute</a>
</p>

<p align="center">
  <b>API developed with the goal of using it for a pet adoption system.</b>
</p>

<h2 id="started">🚀 Getting started</h2>

To run this project, you need to execute the container with PostgreSQL, create the database, and adjust the environment
variables according to the configurations you've defined in both `application.properties` and `docker-compose.yaml`. 
By doing so, you will be ready to run the API normally.

<h3>Prerequisites</h3>

Here you list all prerequisites necessary for running your project. For example:

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Docker](https://docs.docker.com/get-docker/)

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
spring.datasource.password=${DB_PASSW:admin}
```

Don't forget to change this enviroment variables in the `docker-compose.yaml` file too:

```yaml
environment:
  POSTGRES_DB: "adopet"
  POSTGRES_PASSWORD: "admin"
```

<h3>Starting</h3>

To start the project, it is necessary run your database configured first, for that, you just need to execute this command:

```bash
docker-compose up -d 
``````
Doing that, run the project in your favourite IDE and send requests using HTTP methods in this endopoint: `localhost:8080`.


<h2 id="routes">📍 API Endpoints</h2>

Here you will find all the endpoints, HTTP methods accepted and their descriptions.

<h3>Shelters</h3>

| route                         | description                                            |
|-------------------------------|--------------------------------------------------------|
| <kbd>GET /shelters</kbd>      | Retrieves a page containing 10 shelters each.          |
| <kbd>GET /shelters/{id}</kbd> | Retrieves  a shelter according to id in path variable. |
| <kbd>POST /shelters</kbd>     | Register a shelter.                                    |
| <kbd>PUT /shelters</kbd>      | Update a shelter.                                      |


<h2 id="contribute">📫 Contribute</h2>

To contribute, you be able to use git flow, you just need to follow this steps:

1. `git clone git clone https://github.com/CrysLef/adopet.git`
2. `git flow feature start FEATURE_NAME`
3. Follow commit patterns using `feat: ` when you need to add a feature in the project
4. Finished the feature, you just need to execute this command: `git flow feature finish FEATURE_NAME`
5. Open a Pull Request explaining the problem solved or feature made, if exists, append screenshot of visual modifications and wait for the review!

<h3>Documentations that might help</h3>

[📝 How to create a Pull Request](https://www.atlassian.com/br/git/tutorials/making-a-pull-request)

[💾 Commit pattern](https://gist.github.com/joshbuchea/6f47e86d2510bce28f8e7f42ae84c716)