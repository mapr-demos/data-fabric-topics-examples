# Python Examples
Python Pub/Sub Examples for HPE Ezmeral Data Fabric Topics

## Install kafka-python: pip3 install kafka-python
`pip3 install kafka-python`
`pip3 list | grep kafka-python`

### Running Examples
### [Producing Messages](src/main/Produce.py)
python3 src/main/Produce.py <bootstrap server: port> <topicname>
`python3 src/main/Produce.py localhost:9092 topicTestKafkaPythonClient`

### [Consuming Messages](src/main/Consume.Consume.py)
python3 src/main/Consume.py <bootstrap server: port> <topicname>
`python3 src/main/Consume.py localhost:9092 topicTestKafkaPythonClient`
