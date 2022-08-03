# kakfa-streams-example

## domain-crawler
Rest API to fetch domain info from https://domainsdb.info
Sends each domain object over to kafka topic
Uses Spring Kafka template

## domain-processor
Filter the received domain objects from kafka topic based on Active flag
Sends active domain object over to kafka topic
Uses Spring Cloud Streams for Kafka


## domain-service
Receives the active domain objects from kafka topic
Prints domain object
Uses Spring Cloud Streams for Kafka
