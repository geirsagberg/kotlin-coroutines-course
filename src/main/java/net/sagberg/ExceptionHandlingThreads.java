package net.sagberg;

public class ExceptionHandlingThreads {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            throw new RuntimeException("Error in thread");
        });

        thread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println("Caught exception from thread: " + e.getMessage());
        });

        thread.start();

        System.out.println("Main thread continues running.");
    }
}
