/* Copyright (c) 2023 & onwards. Hewlett Packard Enterprise Company, All rights reserved */
package com.hpe.ezmeral.topics;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

public class Demo_4_ConsumerExample {

  public static void main(final String[] args) {
    final Properties config = new Properties();
    config.putAll(Demo_0_Constants.COMMON_PROPS);

    config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
    config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    // Uncomment the following to consume messages from the beginning
    // config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

    final KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(config);
    
    final TopicPartition topicPartition = new TopicPartition(Demo_0_Constants.TOPIC_NAME, 0);
    consumer.assign(Arrays.asList(topicPartition));
    System.out.printf("Consuming messages from topic '%s'.%n", Demo_0_Constants.TOPIC_NAME);

    boolean forEver = true;
    while (forEver) {
      final ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
      for (final ConsumerRecord<String, String> record : records) {
        System.out.printf("Msg received -> (Key = '%s', value = '%s'). Press Ctrl-C to stop.%n", record.key(), record.value());
      }
    }

    consumer.close();
  }

}
