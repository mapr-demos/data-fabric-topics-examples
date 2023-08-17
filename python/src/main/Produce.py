# Kafka-python producer example
from kafka import KafkaProducer
import sys
import time

#Accepts user provided DataAccessGateway/Broker IP or hostname followed by a port number. 
#The IP address/hostname and port number are separted by a colon "<localhost:9092>".
server = sys.argv[1]
print("DataAccessGateway/Broker: " + server)

#Accepts user provided one kafka topic name.
topic = sys.argv[2]
print("Topic:  " + topic)

#Producer produces 100 messages of the format 'Python msg 0' to 'Python msg 99'.
#The messages are produced to the user provided topic name.
#Expected Output: List of all produced messages from 'Python msg 0' to 'Python msg 99'
print("Starting Producer")
producer = KafkaProducer(bootstrap_servers=[server])
numMsgs = 0
for _ in range(100):
    msg = 'Python msg ' + str(numMsgs)
    produce_msg = bytes(msg, 'utf-8')
    producer.send(topic, produce_msg)
    print("Sent message: " + str(produce_msg))
    numMsgs += 1
producer.flush()
print("Messages produced: " + str(numMsgs))
