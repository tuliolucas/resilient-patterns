# Building Resilient Microservices: Concepts & Java Implementations  
> Inspired by the **Patterns for Building Resilient Microservices** [O’Reilly Live](https://learning.oreilly.com), presented by [Sam Newman](https://samnewman.io/)    

![GitHub stars](https://img.shields.io/github/stars/tuliolucas/resilient-patterns?style=social)
![GitHub forks](https://img.shields.io/github/forks/tuliolucas/resilient-patterns?style=social)
![GitHub last commit](https://img.shields.io/github/last-commit/tuliolucas/resilient-patterns)

## Table of Contents
- [What is Resilience?](#what-is-resilience)
- [Golden Rules of Distributed Computing](#golden-rules-of-distributed-computing)
- [Resilience Concepts](#resilience-concepts)
- [Design Patterns](#design-patterns)
- [Implementation Examples](#implementation-examples)
- [Getting Started](#getting-started)
- [Contributing](#contributing)

---

## What is Resilience?
Resilience in microservices means:
- Reducing the impact of failures.  
- Ensuring the system can recover quickly.  
- Handling unexpected issues gracefully.

## Golden Rules of Distributed Computing
1️⃣ **You can't beam information between two points instantly.**  
2️⃣ **Sometimes, the service you depend on will be unavailable.**  

## Resilience Concepts (Woods' Model)
| Concept               | Description                                      | Example |
|-----------------------|--------------------------------------------------|---------|
| **Rebound**           | Recovering after failure                         | Restarting a system |
| **Robustness**        | Absorbing failures                               | Kubernetes pod restart |
| **Graceful Extensibility** | Handling unexpected load changes            | Auto-scaling |
| **Sustained Adaptability** | Improving over time                         | AWS evolving from books to cloud |

## Resilience Design Patterns
 **Circuit Breaker** – Prevents repeated failures due to an unavailable system.  
 **Bulkhead** – Isolates failures to prevent system-wide crashes.  
 **Load Shedding** – Rejects excess requests to maintain stability.  
 **Back Pressure** – Notifies consumers when the system is overwhelmed.  
 **Retry & Timeout** – Ensures fault tolerance through controlled retries.

### Implementation Examples  
 [Circuit Breaker](src/main/java/com/example/resilience/CircuitBreaker.java)  
 [Bulkhead](src/main/java/com/example/resilience/Bulkhead.java)  
 [Load Shedding](src/main/java/com/example/resilience/LoadShedding.java)  
 [Back Pressure](src/main/java/com/example/resilience/Backpressure.java)  
 [Retry](src/main/java/com/example/resilience/Retry.java)  
 [Timeout](src/main/java/com/example/resilience/Timeout.java)  


## Getting Started
```sh
# Clone the repo
git clone https://github.com/user/repository.git
cd repository
