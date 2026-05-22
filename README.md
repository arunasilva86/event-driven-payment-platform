# About the project

This is a sample project that explain the usage of Kafka as a message broker.
It's a multimodule maven project with producer service and consumer service are designed as separate modules

# How to run the project

* Make sure you have docker engine (or Docker desktop) installed and running
* Runthe mvn clean package command from the project root to build consumer-service and producer-service jar files. 
  *  **`mvn clean package`**
* Run the docker-compose.yml from the project root to create and start all docker containers (producer-service/consumer-service/kafka broker/kafka-ui/mongodb-replicaset)
  *  **`docker compose up --build -d`**
* * Log in to one of the mongo nodes(Ex: mongo1) and initialize the replica set with below command
  * `docker exec -it mongo1 mongosh`
  * `rs.initiate({_id: "rs0",
        members: [
        { _id: 0, host: "mongo1:27017" },
        { _id: 1, host: "mongo2:27017" },
        { _id: 2, host: "mongo3:27017" }
        ]
        })`
* Producer is sending a message in every 3 seconds to the kafka broker which supposed to be consumed by the consumer and persisted to the mongo db cluster
* Kafka ui is available in : http://localhost:8090/

# Architecture

![Architecture](docs/architecture.png)
