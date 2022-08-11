#!/bin/sh

# Create a topic named orders
# docker exec easy-shop-kafka kafka-topics --bootstrap-server easy-shop-kafka:9092 --create --topic orders

# List kafka topics
# docker exec easy-shop-kafka kafka-topics --bootstrap-server easy-shop-kafka:9092 --list

# Delete a specific topic
# docker exec easy-shop-kafka kafka-topics --bootstrap-server easy-shop-kafka:9092 --delete --topic orders

# Read messages from the topic
# docker exec --interactive --tty easy-shop-kafka kafka-console-consumer --bootstrap-server easy-shop-kafka:9092 --topic orders --from-beginning

docker exec --interactive --tty easy-shop-kafka kafka-console-producer --bootstrap-server easy-shop-kafka:9092 --topic orders
