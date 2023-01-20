/* Copyright (c) 2023 & onwards. Hewlett Packard Enterprise Company, All rights reserved */
package com.hpe.ezmeral.topics;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.TopicListing;

public class Demo_2_ListTopics {

  public static void main(final String[] args) throws ExecutionException, InterruptedException {
    final Properties config = new Properties();
    config.putAll(Demo_0_Constants.COMMON_PROPS);

    final AdminClient admin = AdminClient.create(config);

    System.out.println("Topics in this Data Fabric cluster:");
    for (final TopicListing topicListing : admin.listTopics().listings().get()) {
      System.out.println(topicListing);
    }
    
    admin.close();
  }

}
