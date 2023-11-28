# Threads vs Coroutines

| Criteria                 | Threads                             | Coroutines                              |
|--------------------------|-------------------------------------|-----------------------------------------|
| Nature                   | Preemptive                          | Cooperative                             |
| Memory consumption       | Higher                              | Lower                                   |
| Creation and destruction | Expensive                           | Cheap                                   |
| Concurrency model        | Parallel                            | Concurrent but not necessarily parallel |
| Lifecycle management     | OS/hardware-level                   | Application/library-level               |
| Scalability              | Limited                             | Unlimited*                              |
| Error Handling           | OS-level (e.g., segmentation fault) | Application-level (exceptions)          |

**DEMO:**

- [10000 threads](../src/main/java/net/sagberg/MemoryThreads.java)
- [10000 coroutines](../src/main/java/net/sagberg/MemoryCoroutines.kt)

[Neste - Concurrency i Java](03-concurrency-java-threads.md)
