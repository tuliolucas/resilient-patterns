package com.example.resilience;

import java.time.Duration;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Backpressure {

  public static void main(String[] args) throws InterruptedException {
    Flux<Long> fastProducer = Flux.interval(Duration.ofMillis(1)) // Produces events extremely fast
        .onBackpressureDrop(item -> System.out.println("Dropped: " + item)) // Now, events are actually dropped
        .doOnNext(item -> System.out.println("Emitted: " + item));

    fastProducer
        .publishOn(Schedulers.boundedElastic(), 1) // Sets a ridiculously small buffer
        .subscribe(
            item -> {
              try {
                Thread.sleep(1000); // Extremely slow consumer (1 item every 1s)
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              System.out.println("Processed: " + item);
            });

    Thread.sleep(30000); // Keeps the application alive long enough
  }
}
