# kafka-python Examples
Python Pub/Sub Examples for HPE Ezmeral Data Fabric Topics. This works with the Apache Kafka Wire level Protocol. 
This module contains Python producer and consumer programs.These programs accept the following 2 arguments:
1. Bootstrap server and the port: <DataAccessGateway ip address or hostname: port> 
2. One kafka topic.

Note: For more details look at 'Running Producer Example' and 'Running Consumer Example' section below.

# About Topic creation: 
The topic is auto created by default. If the user chooses, cli can also used to create the topic.
1. Auto Topic Creation: By default the auto topic creation is enabled on the cluster. So, when a producer or consumer program is run, the specified topic is auto created. 
2. User Topic Creation: If the user intends to precreate topics, it can done using the "maprcli kafkatopic create -topic <topicname>". Refer to https://docs.ezmeral.hpe.com/datafabric-customer-managed/74/ReferenceGuide/kafkatopic.html
Note: In both above cases the topic owner has all permissions on the topic.


# Prerequistes: 
1. Install kafka-python: pip3 install kafka-python
`pip3 install kafka-python`
`pip3 list | grep kafka-python`
2. Install secure Data Fabric cluster with DataAccessGateway. Refer to https://docs.ezmeral.hpe.com/datafabric-customer-managed/74/MapR_Streams/kwps-get-started.html

# Running Producer Example
This is a kafka-python producer. The producer produces 100 messages to the specified topic.
Format : python3 src/main/Produce.py -b <bootstrap server: port> -t <one topicname> -c <number of messages>
Example: `python3 src/main/Produce.py -b localhost:9092 -t topicTestKafkaPythonClient -c 200`
Expected Output: List of all produced messages from 'Python msg 0' to 'Python msg 99'

# Running Consumer Example
This is a kafka-python consumer. The consumer consumes messages from the specified topic.
Format : python3 src/main/Consume.py -b <bootstrap server: port> -t <one topicname>
Example: `python3 src/main/Consume.py -b localhost:9092 -t topicTestKafkaPythonClient`
Expected Output: List of all the consumed messages


