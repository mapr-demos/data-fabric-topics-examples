/* Copyright (c) 2023 & onwards. Hewlett Packard Enterprise Company, All rights reserved */
package com.hpe.ezmeral.topics;

import java.util.Properties;

import org.apache.kafka.clients.CommonClientConfigs;

public class Demo_0_Constants {

  private static final String BAD_HOST = "<<bad-host>>";

  public static final String TOPIC_SERVER_HOST = BAD_HOST;

  public static final int TOPIC_SERVER_PORT = 9092;

  public static final String TOPIC_NAME = "fast-messages";

  public static final Properties COMMON_PROPS = new Properties();
  static {
    if (TOPIC_SERVER_HOST.equals(BAD_HOST)) {
      System.out.println("Error: DFaaS Data Access Gateway Server is not set correctly.\n"
          + "Please update the value in Demo_0_Constants.java.");
      System.exit(1);
    }
    COMMON_PROPS.setProperty(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, TOPIC_SERVER_HOST + ":" + TOPIC_SERVER_PORT);
  }

}
