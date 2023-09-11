# Samtidighet i Java

- Java 8 kom med `CompletableFuture`
- Sammenlignbar med `Task` i C# eller `Promise` i JavaScript

## (Completable) Future

```java
public class Life {
    void morningRoutine() {
        var coffeeBrewing = CompletableFuture.supplyAsync(() -> {
            Thread.sleep(30000);
            return new Coffee();
        });
        var catPeeing = CompletableFuture.runAsync(() -> {
            Thread.sleep(10000 + Math.random() * 60000);
            // Cat wants in
        });
        brushTeeth();
        catPeeing.wait();
        var coffee = coffeeBrewing.get();
        startWorkingDay(coffee);
    }
}
```

[Neste](05-concurrency-kotlin.md)
