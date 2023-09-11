# Samtidighet i Java

Eksempel med både parallelle og sekvensielle operasjoner.

## Threads

- `Thread.sleep()` blokker sin tråd

```java
public class Life {
    void morningRoutine() {
        Coffee coffee = null;
        var coffeeBrewingThread = new Thread(() -> {
            // Start coffee
            Thread.sleep(30000);
            coffee = new Coffee();
        });
        coffeeBrewingThread.start();
        var catPeeingThread = new Thread(() -> {
            // Let out cat
            Thread.sleep(10000 + Math.random() * 60000);
        });
        catPeeingThread.start();
        brushTeeth();
        catPeeingThread.join();
        coffeeBrewingThread.join();
        startWorkingDay(coffee);
    }
}
```

[Neste](04-concurrency-java-future.md)
