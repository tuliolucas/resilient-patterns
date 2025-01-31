## Building Resilient Microservices: Concepts & Java Implementations
### Hosted by [Sam Newman](https://samnewman.io/)

This is inspired in the Patterns for Building Resilient Microservices [O’Reilly Live](https://learning.oreilly.com)

*First of all, what does resilience in microservice mean?*
The ability of reduce something failing and if it fails, how to reduce the damage. 

### Golden Rules of distribute computing

1. You can't beam information between two points instantly.
2. Sometimes, the thing you want to talk to is not there.

**Practical Applications of Woods' Four Resilience Concepts**

- Rebound: Ability to recover after a traumatic event. E.g.: Restarting a crashed system;
- Robustness: Ability to absorb "perturbations". E.g.: Kubernetes Pod dies;
- Graceful extensibility: Handling the unexpected. E.g.: Auto-scaling servers;
- Sustained Adaptability: Improve over time to stay resilient. E.g.: Amazon shifting from books to AWS.

## Robustness-Complexity Trade-off
**More robustness = More complexity and potential vulnerabilities**

Design Patterns:
- Circuit break 
- Bulkhead 
- Load shedding 
- Back pressure 
- Retry
- Timeout

### Circuit break Pattern

 In a nutshell the reason for this pattern is to avoid the system to continue trying to communicate with a unavailable system. Avoiding the overload and help the recovery.
Example implementation: [CircuitBreaker.java](src/main/java/com/example/resilience/CircuitBreaker.java)

### Bulkheads Pattern

This pattern is about how to minimize / isolate resources to avoid overload. In this pattern the resource will be protected but here the requester waits for its turn.
Example implementation: [Bulkheads.java](src/main/java/com/example/resilience/Bulkhead.java)

### Load Shedding Pattern

This pattern mainly focus on reject more processing and/or request for example when the system is overload. In this pattern the requester will be rejected.
Example implementation: [LoadShedding.java](src/main/java/com/example/resilience/LoadShedding.java)

### Back pressure Pattern

This pattern is mainly focus to tell to the consumer that its needs to stop to request because of a instability.
Example implementation: [Backpressure.java](src/main/java/com/example/resilience/Backpressure.java)

### Retry Pattern
The Retry pattern ensures that when a transient failure occurs, the system automatically retries the operation before giving up. 

### Timeout Pattern
The Timeout pattern prevents the system from waiting indefinitely for a response from a slow or failing service. 
