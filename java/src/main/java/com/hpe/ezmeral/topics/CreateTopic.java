/* Copyright (c) 2023 & onwards. Hewlett Packard Enterprise Company, All rights reserved */
package com.hpe.ezmeral.topics;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;

public class CreateTopic {
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
    final NewTopic newTopic = new NewTopic(cmdLine.getTopicName(), 1, (short) 1);

    System.out.printf("Creating new topic '%s' in this Data Fabric cluster.%n", cmdLine.getTopicName());
    admin.createTopics(Collections.singleton(newTopic)).all().get();
    System.out.printf("Topic '%s' created.%n", cmdLine.getTopicName());

    admin.close();
  }

}
