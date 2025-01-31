package com.example.resilience;

import java.util.concurrent.Semaphore;
import java.security.SecureRandom;

public class Bulkhead {

  private static final int MAX_CONCURRENT_REQUESTS = 5;
  private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
  private static final SecureRandom RANDOM = new SecureRandom();
  public static final int REQUESTERS = 5;

  private final Semaphore semaphore;

  public Bulkhead(int maxConcurrentRequests) {
    this.semaphore = new Semaphore(maxConcurrentRequests);
  }

  public String accessResource() {
    try {
      semaphore.acquire();
      System.out.println(Thread.currentThread().getName() + " accessing...");

      Thread.sleep(2000);  // Simulate processing time

      return generateRandomString();  // Return a random string resource

    } catch (InterruptedException e) {
      e.printStackTrace();
      return "Error";
    } finally {
      semaphore.release();
    }
  }

  private String generateRandomString() {
    int capacity = 10;
    StringBuilder sb = new StringBuilder(capacity);
    for (int i = 0; i < capacity; i++) {
      sb.append(CHARACTERS.charAt( RANDOM.nextInt(CHARACTERS.length())));
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    Bulkhead bulkhead = new Bulkhead(MAX_CONCURRENT_REQUESTS);  // Allow only 2 concurrent accesses

    for (int i = 0; i < REQUESTERS; i++) {
      new Thread(() -> {
        String result = bulkhead.accessResource();
        System.out.println(Thread.currentThread().getName() + " received: " + result);
      }).start();
    }
  }
}
