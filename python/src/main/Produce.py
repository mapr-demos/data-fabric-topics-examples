# Kafka-python producer example
from kafka import KafkaProducer
import sys
import time
import argparse
import re

# Parse all the user provided arguments
parser = argparse.ArgumentParser(
                    prog='python3 src/main/Produce.py',
                    description='Generates a number of messages and sends them to a particular Kafka topic')
parser.add_argument('-c', '--count', default=100, help='Number of messages to send to kafka topic(default 100)')
parser.add_argument('-b', '--broker', help='host:port for the bootstrap server', required=True)
parser.add_argument('-t', '--topic', default='pythonTestTopic', help='The kafka topic where the messages are sent(default pythonTestTopic)')

args = parser.parse_args()

# Check the host:port format for the bootstrap server input.
if not re.match("[^ :]+:\\d+", args.broker):
    print("Hostname should be in the form host:port")
    parser.print_help()
    sys.exit()

# User provided DataAccessGateway/Broker IP or hostname followed by a port number. 
server = args.broker
print("DataAccessGateway/Broker: " + server)

# User provided kafka topic name is used or if not provided the default 'pythonTestTopic' is used.
topic = 'pythonTestTopic'
if (args.topic):
	topic = args.topic
print("Kafka Topic Name:  " + topic)

# User provided message count is used or if not provided by default  100 messages are sent. 
count = 100
if (args.count):
        count = int(args.count)
print("Message Count: " + str(count))

# Producer produces (default 100 or count given by the user) messages of the format 'Python msg <count>'.
# The messages are produced to the user provided topic name.
# Expected Output: List of all produced messages 'Python msg <count>'.
print("Starting Producer")
producer = KafkaProducer(bootstrap_servers=[server])
numMsgs = 0
for _ in range(count):
    msg = 'Python msg ' + str(numMsgs)
    produce_msg = bytes(msg, 'utf-8')
    producer.send(topic, produce_msg)
    print("Sent message: " + str(produce_msg))
    numMsgs += 1
producer.flush()
print("Messages produced: " + str(numMsgs))
