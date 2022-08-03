# kakfa-streams-example

## docker-compose.yml
Contains docker commands for starting Confluent Kafka on Docker.<br>
Requires docker & docker-compose installed locally.<br>
Run in detach mode:<br>

$ docker-compose up -d

## domain-crawler
Rest API to fetch domain info from https://domainsdb.info<br>
Sends each domain object over to kafka topic<br>
Uses Spring Kafka template

## domain-processor
Filter the received domain objects from kafka topic based on Active flag<br>
Sends active domain object over to kafka topic<br>
Uses Spring Cloud Streams for Kafka


## domain-service
Receives the active domain objects from kafka topic<br>
Prints domain object<br>
Uses Spring Cloud Streams for Kafka
