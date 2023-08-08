/* Copyright (c) 2023 & onwards. Hewlett Packard Enterprise Company, All rights reserved */
package com.hpe.ezmeral.topics;

import java.io.Console;
import java.util.Properties;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.security.auth.SecurityProtocol;

public class CommandLine {

  private final Properties config = new Properties();

  private String topicName;

  public CommandLine(String ...args) {
    String userName = null;
    String password = null;

    for (int i = 0; i < args.length; i++) {
      final String argKey = args[i];
      switch (argKey) {
      case "--bootstrap-server":
        config.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, getArgValue(args, i++));
        break;
      case "--from-beginning":
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        break;
      case "--topic":
        topicName = getArgValue(args, i++);
        break;
      case "--user":
        userName = getArgValue(args, i++);
        break;
      case "--password":
        password = getArgValue(args, i++);
        break;
      default:
        throw new IllegalArgumentException("Unknown command line argument " + argKey);
      }
    }

    if (userName != null) {
      if (password == null) {
        final Console console = System.console();
        if (console == null) {
          throw new IllegalStateException("System console is not available. Can not read password.");
        }
        final char[] passwordChr = console.readPassword("Enter password: ");
        password = new String(passwordChr);
      }
      config.setProperty(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, SecurityProtocol.SASL_PLAINTEXT.name);
      config.setProperty(SaslConfigs.SASL_MECHANISM, "PLAIN");
      config.setProperty(SaslConfigs.SASL_JAAS_CONFIG, "org.apache.kafka.common.security.plain.PlainLoginModule required "
          + "username=\"" + userName + "\" "
          + "password=\"" + password + "\";");
    }
  }

  public Properties getCommonConfig() {
    return config;
  }

  public String getTopicName() {
    if (topicName == null) {
      throw new IllegalArgumentException("A topic name must be specified using \"--topic\" argument.");
    }
    return topicName;
  }

  private String getArgValue(String[] args, int i) {
    if (i == (args.length - 1)) {
      throw new IllegalArgumentException("Missing value for command line argument " + args[i]);
    }
    return args[i+1];
  }

}
