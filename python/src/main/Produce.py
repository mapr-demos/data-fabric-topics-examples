from kafka import KafkaProducer
import sys
import time

server = sys.argv[1]
print("Broker: " + server)
topic = sys.argv[2]
print("Topic:  " + topic)


print("Starting Producer")
producer = KafkaProducer(bootstrap_servers=[server])
numMsgs = 0
for _ in range(1000):
    msg = 'Python msg ' + str(numMsgs)
    produce_msg = bytes(msg, 'utf-8')
    producer.send(topic, produce_msg)
    numMsgs += 1
producer.flush()
print("Messages produced: " + str(numMsgs))
