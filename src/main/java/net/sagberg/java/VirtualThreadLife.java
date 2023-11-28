package net.sagberg.java;

import java.util.concurrent.atomic.AtomicReference;

import static net.sagberg.java.Statics.brushTeeth;
import static net.sagberg.java.Statics.startWorkingDay;

public class VirtualThreadLife {
    public static void main(String[] args) throws InterruptedException {
        morningRoutine();
    }

    public static void morningRoutine() throws InterruptedException {
        AtomicReference<Coffee> coffee = new AtomicReference<>();

        // Replace with virtual thread
        var coffeeBrewingThread = Thread.startVirtualThread(() -> {
            System.out.println("Brewing coffee");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Coffee done!");
            coffee.set(new Coffee());
        });

        // Replace with virtual thread
        var catPeeingThread = Thread.startVirtualThread(() -> {
            System.out.println("Letting out cat");
            try {
                Thread.sleep((long) (1000 + Math.random() * 6000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Cat wants in");
        });

        brushTeeth();
        catPeeingThread.join();
        coffeeBrewingThread.join();
        startWorkingDay(coffee.get());
    }

    // Other methods like `brushTeeth` and `startWorkingDay` remain the same
    // Coffee class remains the same
}
