/* Copyright (c) 2023 & onwards. Hewlett Packard Enterprise Company, All rights reserved */
package com.hpe.ezmeral.topics;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class Produce {
  public static void main(final String[] args) throws InterruptedException {
    try {
      run(args);
    } catch (Exception e) {
      System.err.println("ERROR: " + e.getMessage());
      System.exit(1);
    }
  }

  private static void run(final String[] args) throws InterruptedException {
    final CommandLine cmdLine = new CommandLine(args);

    final Properties config = new Properties();
    config.putAll(cmdLine.getCommonConfig());
    config.put(ProducerConfig.ACKS_CONFIG, "all");
    config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

    final Producer<String, String> producer = new KafkaProducer<>(config);
    final String topicName = cmdLine.getTopicName();
    System.out.printf("Producing to topic '%s'.%n", topicName);
    System.out.printf("Enter messages. Press Ctrl-C to stop.%n");

    final int msgCount = 1000;
    for (int msgIdx = 1; msgIdx <= msgCount; msgIdx++) {
      final String key = String.format("Key-%d", msgIdx);
      final String val = System.console().readLine(">");
      producer.send(new ProducerRecord<>(topicName, key, val));
    }

    producer.close();
  }

}
