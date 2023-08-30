#Kafka-python consumer exmaple
from kafka import KafkaConsumer
import sys
import time
import argparse
import re

# Parse all the user provided arguments
parser = argparse.ArgumentParser(
                    prog='python3 src/main/Consume.py',
                    description='Consumes messages from a particular Kafka topic')
parser.add_argument('-b', '--broker', help='host:port for the bootstrap server', required=True)
parser.add_argument('-t', '--topic',  help='The kafka topic from which the messages are consumed', required=True)

args = parser.parse_args()

# Check the host:port format for the bootstrap server input.
if not re.match("[^ :]+:\\d+", args.broker):
    print("Hostname should be in the form host:port")
    parser.print_help()
    sys.exit()

# User provided DataAccessGateway/Broker IP or hostname followed by a port number.
server = args.broker
print("DataAccessGateway/Broker: " + server)

# User provided kafka topic name is used to consume the messages.
topic = args.topic
print("Kafka Topic Name:  " + topic)

# Consumer consumes messages from the user provided topic from the earliest message. 
# Expected Output: List of all consumed messages.
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
