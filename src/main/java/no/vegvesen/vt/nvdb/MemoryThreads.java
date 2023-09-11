package no.vegvesen.vt.nvdb;

public class MemoryThreads {
    public static void main(String[] args) {
        Thread[] threads = new Thread[10000];

        // Start threads
        for (int i = 0; i < 10000; i++) {
            final int threadNumber = i + 1;
            threads[i] = new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Hello from thread " + threadNumber);
            });
            threads[i].start();
        }

        // Join threads
        for (int i = 0; i < 10000; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
