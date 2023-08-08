/* Copyright (c) 2023 & onwards. Hewlett Packard Enterprise Company, All rights reserved */
package com.hpe.ezmeral.topics;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

public class Consume {
  public static void main(final String[] args) throws InterruptedException {
    try {
      run(args);
    } catch (Exception e) {
      System.err.println("ERROR: " + e.getMessage());
      System.exit(1);
    }
  }

  private static void run(final String[] args) {
    final CommandLine cmdLine = new CommandLine(args);

    final Properties config = new Properties();
    config.putAll(cmdLine.getCommonConfig());
    config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
    config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

    final Consumer<String, String> consumer = new KafkaConsumer<String, String>(config);
    final String topicName = cmdLine.getTopicName();
    final TopicPartition topicPartition = new TopicPartition(topicName, 0);
    consumer.assign(Arrays.asList(topicPartition));
    System.out.printf("Consuming messages from topic '%s'. Press Ctrl-C to stop.%n", topicName);

    boolean forEver = true;
    while (forEver) {
      final ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
      for (final ConsumerRecord<String, String> record : records) {
        System.out.printf("Msg received -> (Key = '%s', value = '%s').%n", record.key(), record.value());
      }
    }

    consumer.close();
  }

}
