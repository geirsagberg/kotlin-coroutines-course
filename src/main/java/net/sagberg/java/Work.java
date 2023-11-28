package net.sagberg.java;

import net.sagberg.Pizza;
import net.sagberg.Report;

import java.util.concurrent.CompletableFuture;

public class Work {
    public CompletableFuture<Report> requestReport() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public CompletableFuture<Void> sendEmail(Report content) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public CompletableFuture<Pizza> orderPizza() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void eatPizza(Pizza pizza) {
    }

    public void relax() {
    }

    public void work() {
        requestReport()
            .thenCompose(this::sendEmail)
            .thenCompose(x -> orderPizza())
            .thenAccept(this::eatPizza)
            .thenRun(this::relax);
    }
}
