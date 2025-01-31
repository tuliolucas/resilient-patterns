package com.example.resilience;

import java.security.SecureRandom;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class LoadShedding {

  private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
  private static final SecureRandom RANDOM = new SecureRandom();
  public static final int REQUESTERS = 15;  // More requesters to increase rejection rate
  public static final int MAX_CONCURRENT_REQUESTS = 2;

  private final Semaphore semaphore;

  public LoadShedding(int maxConcurrentRequests) {
    this.semaphore = new Semaphore(maxConcurrentRequests);
  }

  public String accessResource() {
    try {
      System.out.println(Thread.currentThread().getName() + " Trying to access...");

      if (!semaphore.tryAcquire(50, TimeUnit.MILLISECONDS)) { // Lower timeout to cause more rejections
        System.out.println(Thread.currentThread().getName() + " Request dropped due to high load!");
        return "Rejected";
      }

      System.out.println(Thread.currentThread().getName() + " Access granted!");

      Thread.sleep(2000);  // Simulate processing time

      return generateRandomString();  // Return a random string resource

    } catch (InterruptedException e) {
      e.printStackTrace();
      return "Error";
    } finally {
      semaphore.release();
      System.out.println(Thread.currentThread().getName() + " Released the resource.");
    }
  }

  private String generateRandomString() {
    int capacity = 10;
    StringBuilder sb = new StringBuilder(capacity);
    for (int i = 0; i < capacity; i++) {
      int index = RANDOM.nextInt(CHARACTERS.length());
      sb.append(CHARACTERS.charAt(index));
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    LoadShedding loadShedding = new LoadShedding(MAX_CONCURRENT_REQUESTS);  // Allow only 2 concurrent accesses

    for (int i = 0; i < REQUESTERS; i++) {
      new Thread(() -> {
        String result = loadShedding.accessResource();
        System.out.println(Thread.currentThread().getName() + " Received: " + result);
      }).start();
    }
  }
}
