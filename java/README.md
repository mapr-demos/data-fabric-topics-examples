# Java Examples
Java Pub/Sub Examples for HPE Ezmeral Data Fabric Topics

## Compiling
`mvn clean install`

## Running Examples

### [Creating Topic](src/main/java/com/hpe/ezmeral/topics/CreateTopic.java)
`mvn exec:java -Dexec.mainClass="com.hpe.ezmeral.topics.CreateTopic" -Dexec.args="--bootstrap-server localhost:9092 --user root --topic newTopic"`

### [Listing Topics](src/main/java/com/hpe/ezmeral/topics/ListTopics.java)
`mvn exec:java -Dexec.mainClass="com.hpe.ezmeral.topics.ListTopics" -Dexec.args="--bootstrap-server localhost:9092 --user root"`

### [Producing Messages](src/main/java/com/hpe/ezmeral/topics/Produce.java)
`mvn exec:java -Dexec.mainClass="com.hpe.ezmeral.topics.Produce" -Dexec.args="--bootstrap-server localhost:9092 --user root --topic newTopic"`

### [Consuming Messages](src/main/java/com/hpe/ezmeral/topics/Consume.java)
`mvn exec:java -Dexec.mainClass="com.hpe.ezmeral.topics.Consume" -Dexec.args="--bootstrap-server localhost:9092 --user root --topic newTopic --from-beginning"`

### [Deleting Topic](src/main/java/com/hpe/ezmeral/topics/DeleteTopic.java)
`mvn exec:java -Dexec.mainClass="com.hpe.ezmeral.topics.DeleteTopic" -Dexec.args="--bootstrap-server localhost:9092 --user root --topic newTopic"`
