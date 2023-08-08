/* Copyright (c) 2023 & onwards. Hewlett Packard Enterprise Company, All rights reserved */
package com.hpe.ezmeral.topics;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.TopicListing;

public class ListTopics {
  public static void main(final String[] args) throws InterruptedException {
    try {
      run(args);
    } catch (Exception e) {
      System.err.println("ERROR: " + e.getMessage());
      System.exit(1);
    }
  }

  private static void run(final String[] args) throws ExecutionException, InterruptedException {
    final CommandLine cmdLine = new CommandLine(args);

    final Properties config = new Properties();
    config.putAll(cmdLine.getCommonConfig());

    final AdminClient admin = AdminClient.create(config);

    System.out.println("Topics in this Data Fabric cluster:");
    for (final TopicListing topicListing : admin.listTopics().listings().get()) {
      System.out.println(topicListing.name());
    }
    
    admin.close();
  }

}
