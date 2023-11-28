package net.sagberg.java;

import java.util.concurrent.atomic.AtomicReference;

import static net.sagberg.java.Statics.brushTeeth;
import static net.sagberg.java.Statics.startWorkingDay;

public class ThreadLife {
    public static void main(String[] args) throws InterruptedException {
        morningRoutine();
    }

    public static void morningRoutine() throws InterruptedException {
        AtomicReference<Coffee> coffee = new AtomicReference<>();
        var coffeeBrewingThread = new Thread(() -> {
            System.out.println("Brewing coffee");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Coffee done!");
            coffee.set(new Coffee());
        });
        coffeeBrewingThread.start();
        var catPeeingThread = new Thread(() -> {
            System.out.println("Letting out cat");
            try {
                Thread.sleep((long) (1000 + Math.random() * 6000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Cat wants in");
        });
        catPeeingThread.start();
        brushTeeth();
        catPeeingThread.join();
        coffeeBrewingThread.join();
        startWorkingDay(coffee.get());
    }
}
