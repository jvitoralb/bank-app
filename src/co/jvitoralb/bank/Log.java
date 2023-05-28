package co.jvitoralb.bank;

import java.time.LocalDateTime;

public class Log {
    private final LocalDateTime date;
    private final String operation;
    private final double amount;

    public Log(String operation, double amount) {
        this.operation = operation;
        this.amount = amount;
        this.date = LocalDateTime.now();
    }

    private String formatDate() {
        var day = date.getDayOfMonth();
        var month = date.getMonthValue();
        var year = date.getYear();

        return (day + "-" + month + "-" + year);
    }

    private String formatTime() {
        return date.getHour() + ":" + date.getMinute();
    }

    private double getAmount() {
        return this.amount;
    }

    public String getFullLog() {
        return String.format(
                "%s de %.2f realizado dia %s às %s.",
                operation, getAmount(), formatDate(), formatTime()
        );
    }
}
