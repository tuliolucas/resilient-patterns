package com.example.resilience;

import java.util.Random;

public class Retry {

  public static void main(String[] args) {
    int maxRetries = 3;
    int attempt = 0;
    boolean success = false;

    while (attempt < maxRetries) {
      try {
        System.out.println("Attempt " + (attempt + 1));
        unreliableService();
        success = true;
        break; // Exit loop if successful
      } catch (Exception e) {
        System.out.println("Failure: " + e.getMessage());
        attempt++;
      }
    }

    if (!success) {
      System.out.println("Operation failed after " + maxRetries + " retries.");
    }
  }

  private static void unreliableService() throws Exception {
    if (new Random().nextInt(3) != 0) { // 66% chance of failure
      throw new RuntimeException("Service unavailable");
    }
    System.out.println("Operation succeeded");
  }
}
