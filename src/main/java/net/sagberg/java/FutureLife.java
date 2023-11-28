package net.sagberg.java;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static net.sagberg.java.Statics.brushTeeth;
import static net.sagberg.java.Statics.startWorkingDay;


public class FutureLife {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        morningRoutine();
    }

    public static void morningRoutine() throws InterruptedException, ExecutionException {
        var coffeeBrewing = CompletableFuture.supplyAsync(() -> {
            System.out.println("Brewing coffee");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Coffee done!");
            return new Coffee();
        });
        var catPeeing = CompletableFuture.runAsync(() -> {
            System.out.println("Letting out cat");
            try {
                Thread.sleep((long) (1000 + Math.random() * 6000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Cat wants in");
        });
        brushTeeth();
        catPeeing.join();
        var coffee = coffeeBrewing.get();
        startWorkingDay(coffee);
    }
}
