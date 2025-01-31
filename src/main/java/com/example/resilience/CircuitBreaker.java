package com.example.resilience;

import java.util.Random;
import java.util.function.Supplier;

public class CircuitBreaker {

  private static final int REQUESTERS = 5;

  private int failureCount = 0;
  private boolean open = false;

  private String callService(Supplier<String> service) {

    if (open) {
      return "Circuit Open: Fallback Response";
    }

    try {
      String response = service.get();
      failureCount = 0;  // Reset failure count on success
      return response;
    } catch (Exception e) {
      failureCount++;
      int failureThreshold = 3;
      if (failureCount >= failureThreshold) {
        open = true;
      }
      return "Failure: " + e.getMessage();
    }
  }

  public static void main(String[] args) {
    CircuitBreaker breaker = new CircuitBreaker();
    Supplier<String> flakyService = () -> {
      if (new Random().nextBoolean()) throw new RuntimeException("Service Down");
      return "Success!";
    };

    for (int i = 0; i < REQUESTERS; i++) {
      System.out.println(breaker.callService(flakyService));
    }
  }
}
