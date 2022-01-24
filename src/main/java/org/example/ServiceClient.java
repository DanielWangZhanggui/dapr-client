/*
 * Copyright (c) Microsoft Corporation and Dapr Contributors.
 * Licensed under the MIT License.
 */

package org.example;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import io.dapr.client.domain.HttpExtension;
/**
 * Service for input binding example.
 * 1. From your repo root, build and install jars:
 * mvn clean install
 * 2. cd to [repo-root]/examples
 * 3. Run :
 * dapr run --components-path ./components/bindings --app-id inputbinding --app-port 3000 \
 *   -- java -jar target/dapr-java-sdk-examples-exec.jar io.dapr.examples.bindings.http.InputBindingExample -p 3000
 */
@SpringBootApplication
public class ServiceClient {

  public static void main(String[] args) throws Exception {
    String SERVICE_APP_ID = "output";
    String METHOD = "say";
    String message = "Tom";
    
    DaprClient client = new DaprClientBuilder().build();
    int count=0; 
      while(true) {
        count++;
       byte[] response = client.invokeMethod(SERVICE_APP_ID, METHOD, message+count, HttpExtension.POST, null,
            byte[].class).block();
        System.out.println(new String(response));
        try {
          Thread.sleep((long) (10000 * Math.random()));
        } catch (InterruptedException e) {
          e.printStackTrace();
          Thread.currentThread().interrupt();
        }
      }
    }
  }
