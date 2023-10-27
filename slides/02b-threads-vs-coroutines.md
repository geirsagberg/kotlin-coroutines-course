# Threads vs Coroutines

| Criteria                 | Threads                                            | Coroutines                                          |
|--------------------------|----------------------------------------------------|-----------------------------------------------------|
| Nature                   | Preemptive                                         | Cooperative                                         |
| Context switching        | Managed by OS; often more expensive                | Managed by the application; usually lightweight     |
| Memory consumption       | Higher (each thread has its stack)                 | Lower (can share the same thread and stack)         |
| Creation and destruction | Relatively expensive                               | Cheap and lightweight                               |
| Concurrency model        | Can run in parallel on multi-core systems          | Concurrent but not necessarily parallel             |
| Control                  | OS can preemptively switch between threads         | Coroutines yield control cooperatively              |
| Use Cases                | CPU-bound tasks, true parallelism                  | I/O-bound tasks, async programming, generators      |
| Lifecycle management     | OS/hardware-level management                       | Application/library-level management                |
| Scalability              | Limited by system resources (e.g., stack space)    | Can handle many thousands due to lightweight nature |
| Error Handling           | OS-level error handling (e.g., segmentation fault) | Application-level error handling                    |

**DEMO:**

- [10000 threads](../src/main/java/net/sagberg/MemoryThreads.java)
- [10000 coroutines](../src/main/java/net/sagberg/MemoryCoroutines.kt)

[Neste](03-concurrency-java-threads.md)
