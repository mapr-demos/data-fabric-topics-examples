/* Copyright (c) 2023 & onwards. Hewlett Packard Enterprise Company, All rights reserved */
package com.hpe.ezmeral.topics;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;

public class Demo_1_CreateTopic {

  public static void main(final String[] args) throws ExecutionException, InterruptedException {
    final Properties config = new Properties();
    config.putAll(Demo_0_Constants.COMMON_PROPS);

    final AdminClient admin = AdminClient.create(config);
    final NewTopic newTopic = new NewTopic(Demo_0_Constants.TOPIC_NAME, 1, (short) 1);

    System.out.printf("Creating new topic '%s' in this Data Fabric cluster.%n", Demo_0_Constants.TOPIC_NAME);
    admin.createTopics(Collections.singleton(newTopic)).all().get();
    System.out.printf("Topic '%s' created.%n", Demo_0_Constants.TOPIC_NAME);

    admin.close();
  }

}
