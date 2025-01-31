package com.example.resilience;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Timeout {

  public static void main(String[] args) {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    Future<String> future = executor.submit(() -> {
      Thread.sleep(3000); // Simulate a slow service (3 seconds delay)
      return "Response received";
    });

    try {
      String result = future.get(2, TimeUnit.SECONDS); // Set timeout to 2 seconds
      System.out.println(result);
    } catch (TimeoutException e) {
      System.out.println("Request timed out!");
    } catch (Exception e) {
      System.out.println("An error occurred: " + e.getMessage());
    } finally {
      executor.shutdown();
    }
  }
}
