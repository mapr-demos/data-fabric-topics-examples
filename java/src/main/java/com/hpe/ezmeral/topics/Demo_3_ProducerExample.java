/* Copyright (c) 2023 & onwards. Hewlett Packard Enterprise Company, All rights reserved */
package com.hpe.ezmeral.topics;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class Demo_3_ProducerExample {

  public static void main(final String[] args) throws InterruptedException {
    final Properties config = new Properties();
    config.putAll(Demo_0_Constants.COMMON_PROPS);
    config.put(ProducerConfig.ACKS_CONFIG, "all");
    config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

    final Producer<String, String> producer = new KafkaProducer<>(config);
    System.out.printf("Producing to topic '%s'.%n", Demo_0_Constants.TOPIC_NAME);

    final int msgCount = 1000;
    for (int msgIdx = 1; msgIdx <= msgCount; msgIdx++) {
      final String key = String.format("Key-%d", msgIdx);
      final String val = "Time at source: " + System.currentTimeMillis();
      System.out.printf("Sending msg -> (Key = '%s', value = '%s'). Press Ctrl-C to stop.%n", key, val);
      producer.send(new ProducerRecord<>(Demo_0_Constants.TOPIC_NAME, key, val));
      Thread.sleep(1000);
    }

    producer.close();
  }

}
