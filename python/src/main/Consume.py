from kafka import KafkaConsumer
import sys
import time

server = sys.argv[1]
print("Broker: " + server)
topic = sys.argv[2]
print("Topic:  " + topic)

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
