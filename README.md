# ezmeral-topics-examples
Pub/Sub Examples for HPE Ezmeral Data Fabric Topics

## Compiling
`mvn clean install`

## Running examples
### Creating Topic
`mvn exec:java -Dexec.mainClass="com.hpe.ezmeral.topics.CreateTopic" -Dexec.args="--bootstrap-server localhost:9092 --user root --topic newTopic"`

### Listing Topics
`mvn exec:java -Dexec.mainClass="com.hpe.ezmeral.topics.ListTopics" -Dexec.args="--bootstrap-server localhost:9092 --user root"`

### Producing Messages
`mvn exec:java -Dexec.mainClass="com.hpe.ezmeral.topics.Produce" -Dexec.args="--bootstrap-server localhost:9092 --user root --topic newTopic"`

### Consuming Messages
`mvn exec:java -Dexec.mainClass="com.hpe.ezmeral.topics.Consume" -Dexec.args="--bootstrap-server localhost:9092 --user root --topic newTopic --from-beginning"`

### Deleting Topic
`mvn exec:java -Dexec.mainClass="com.hpe.ezmeral.topics.DeleteTopic" -Dexec.args="--bootstrap-server localhost:9092 --user root --topic newTopic"`
