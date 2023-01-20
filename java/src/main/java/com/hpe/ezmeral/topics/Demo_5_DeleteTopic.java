/* Copyright (c) 2023 & onwards. Hewlett Packard Enterprise Company, All rights reserved */
package com.hpe.ezmeral.topics;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;

public class Demo_5_DeleteTopic {

  public static void main(final String[] args) throws ExecutionException, InterruptedException {
    final Properties config = new Properties();
    config.putAll(Demo_0_Constants.COMMON_PROPS);

    final AdminClient admin = AdminClient.create(config);

    System.out.printf("Deleting Topic '%s' in this Data Fabric cluster.%n", Demo_0_Constants.TOPIC_NAME);
    admin.deleteTopics(Collections.singleton(Demo_0_Constants.TOPIC_NAME)).all().get();
    System.out.println("Topic deleted");

    admin.close();
  }

}
