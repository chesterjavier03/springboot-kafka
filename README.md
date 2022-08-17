# RateBoard Technical Assignment

## Queue Exercise

This project is a simulation of a service that receives data from a hotel reservation system using a message broker for micro services communication.

Tech Stack:

- Java
- Spring Boot
- Kafka
- Zookeeper
- Websocket

## Getting Started

Install Kafka into your local system

- [Kafka Installtion Quickstart Guide](https://kafka.apache.org/quickstart)

## Start the Kafka Environment

_**NOTE: Your local environment must have Java 8+ installed.**_

Run the following commands in order to start all services in the correct order:

```Terminal
$ bin/zookeeper-server-start.sh config/zookeeper.properties
```

Open another terminal and run:

```Terminal
$ bin/kafka-server-start.sh config/server.properties
```

## Create a topic for the mock service

Open a terminal and run:

```Terminal
$ bin/kafka-topics.sh --create --topic hotel-reservation --bootstrap-server localhost:9092
```

```Terminal
--topic hotel-reservation
```

`hotel-reservation` <--- _**Topic defined for the 2 microservices to subscibe into**_

Run the jar files _**ahotelservice-1.0.0.jar**_ and _**bhotelservice-1.0.0.jar**_

```Terminal
$ java -jar ahotelservice-1.0.0.jar
```

```Terminal
$ java -jar bhotelservice-1.0.0.jar
```

Once up and running run the following commands in your terminal to send the API request:

```Terminal
$ curl --location --request POST 'http://localhost:9000/api/importer' --form 'messages="hotel reservation system test message"'
```

You will notice that in the logs of the running _**QueueExercise-1.0.0.jar**_ and _**QueueExercise2-1.0.0.jar**_

_**ahotelservice-1.0.0.jar**_

```Terminal
[MessageListener] - Listening to topic [hotel-reservation] and send to websocket group [/hotel/group] with message [hotel reservation system test message]
```

_**bhotelservice-1.0.0.jar**_

```Terminal
[MessageListener] - Listening to topic [hotel-reservation] with message [hotel reservation system test message]
```

The log shows that both micro service received the message sent from the API endpoint. This means that both micro service has the data to process independently and can process the data in parallel.

## Design Decisions:

- Using a message broker for event based approach
- Kafka can be scaled/replicated via spring configuration, offers accurate real time data and it supports spring integration
- Service isolation makes the whole system independent from each other. A service can be created or updated without interference from other services. Can be deployed and scaled independently.
- _**What kind of data does the message by the sender NEED to contain to ensure
  they are imported in the correct order?**_ - Using event based approach makes the data in a linear flow so every update from the originator will be the latest data to be used.

## Websockets:

- **Bi-directional protocol** — either client/server can send a message to the other party (In HTTP, the request is always initiated by the client and the response is processed by the server — making HTTP a uni-directional protocol)
- **Full-duplex communication** — client and server can talk to each other independently at the same time.
- Client side application can have the processed data for display from the server
