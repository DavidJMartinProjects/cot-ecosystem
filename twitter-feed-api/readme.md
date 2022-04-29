## Project Description


# Live Twitter Feed Service

This is a Springboot project that creates a real-time stream to Twitter API, returning all tweets based on a set of specified keywords.  

The microservice will store the tweets in its own db before broadcasting all updates to an event topic that can be consumed by other services. 

A REST based controller was also implemented allowing the tweet feed to be consumed via a REST API.

## Technologies & Frameworks

- SpringBoot
- Spring JPA
- H2 
- RabbitMQ
- Twitter4j

## Deployment

This microservice is configured to be deployed independently using:

- Docker (DockerFile)
- Docker Compose (docker-compose.yaml)
- Helm (Helm Chart)
<!-- 

## ToDo
Please Note: this project is WIP

add Dockerfile & docker-compose

add Helm chart

add testcases for controller

Write a proper readme.md to explain the project -->
