# Monolithic Web Application By Spring Boot

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
[![Maven](https://img.shields.io/badge/Maven-3.9.3-yellow.svg)](https://maven.apache.org/download.cgi)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1.3-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

This web application is a simple project that uses the Spring Boot framework. I have incorporated various concepts such
as `IoC`, `Testing`, `Aspect`, `Security`, `Spring Data`, `Spring Validation`,`OpenApi Documentation`, `Spring Rest API`, `JSON`,
and `MapStruct`. This application includes multiple REST API endpoints for performing CRUD operations on persons and
books. Additionally, there are REST API endpoints for loaning and releasing books that individuals have borrowed. The
implementations are organized using Maven, leveraging the Spring framework, and developed using Java 17.

## Table of Contents

* [Introduction](#introduction)
* [Getting Started](#getting_started)
* [Contributing](#contributing)
* [License](#license)

## Introduction

<a name="introduction"></a>
The aim of this project is to create a monolithic project based on the Spring framework. In this project, efforts have
been made to address security concepts, project cross-cutting concerns such as logging, database usage, entity
relationships, versioning, and similar matters.

## Getting Started

<a name="getting_started"></a>
To get started with this project, you'll need the following prerequisites:

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
[![Maven](https://img.shields.io/badge/Maven-3.9.3-yellow.svg)](https://maven.apache.org/download.cgi)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1.3-brightgreen.svg)](https://spring.io/projects/spring-boot)

Follow these steps to set up the project locally:

1. Clone this repository to your local
   machine: ` git clone https://github.com/mohammadsrafiee/monolithic-spring-boot.git `
2. Navigate to the project directory: ` cd monolithic-spring-boot `
3. Build the project using Maven: ` mvn clean package `
4. Run application by IDE or run application by Spring boot command `mvn spring-boot:run -DskipTests`.
5. Open this link http://localhost:8080/swagger-ui/index.html for swagger ui.

## Contributing

<a name="contributing"></a>
Contributions to this project are welcome! If you'd like to contribute, please follow these steps:

1. Fork the repository
2. Create a new branch: ` git checkout -b new-feature `
3. Make your changes and test them thoroughly
4. Commit your changes: ` git commit -am 'Add some feature' `
5. Push the branch to your forked repository: ` git push origin new-feature `
6. Create a new pull request, explaining your changes and their purpose
7. Please make sure to adhere to the project's coding style and conventions.

## Installation

1. Use docker to test build process and create image for spring boot app by this command

   `docker build -t monolithic-spring-boot:1.0 .`
2. Check for the Newly Created Image: After the build process is complete, you can check for the newly created Docker
   image by running:

   `docker images`
3. Run Containers Using the Image: To run containers using the newly created image, you can use the docker run command.
   consider application need mysql database

   `docker run -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/library -e SPRING_DATASOURCE_USERNAME=mysql -e SPRING_DATASOURCE_PASSWORD=mysql123!@# -p 8080:8080 monolithic-spring-boot:1.0 `

4. use docker-compose for generall deployment ``
## License

<a name="license"></a>
This project is licensed under the [MIT License](LICENSE). You are free to use, modify, and distribute the code for both
commercial and non-commercial purposes.
