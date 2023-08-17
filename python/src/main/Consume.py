#Kafka-python consumer exmaple
from kafka import KafkaConsumer
import sys
import time

#Accepts user provided DataAccessGateway/Broker IP or hostname followed by a port number.
#The IP address/hostname and port number are separted by a colon "<localhost:9092>".
server = sys.argv[1]
print("DataAccessGateway/Broker: " + server)

#Accepts user provided one kafka topic name.
topic = sys.argv[2]
print("Topic:  " + topic)

#Consumer consumes messages from the user provided topici from thei earliest message. 
#Expected Output: List of all consumed messages.
print("Starting Consumer")
consumer = KafkaConsumer(bootstrap_servers=[server],
                         auto_offset_reset='earliest')
consumer.subscribe(topic)
numMsgs = 0
for _ in range(10):
    records = consumer.poll(timeout_ms=500)
    for topic_data, consumer_records in records.items():
        for consumer_record in consumer_records:
            print("Received message: " + str(consumer_record.value.decode('utf-8')))
            numMsgs += 1
print("Messages consumed: " + str(numMsgs))
