package co.jvitoralb.bank;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String nome;
    private final String agencia;
    private final String numeroConta;
    private double saldo;
    private final List<String> logs;

    public Account(String nome, String agencia, String numeroConta) {
        this.nome = nome;
        this.agencia = agencia;
        this.numeroConta = numeroConta;
        this.logs = new ArrayList<>();
    }

    public String getNome() {
        return this.nome;
    }

    public String getNumeroConta() {
        return this.numeroConta;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public List<String> getExtrato() {
        return this.logs;
    }

    protected boolean sacar(double amount) {
        if (this.saldo > amount) {
            this.saldo -= amount;
            return true;
        }
        return false;
    }

    protected boolean depositar(double amount) {
        this.saldo += amount;
        return true;
    }

    protected void registerLog(Log lastLog) {
        logs.add(lastLog.getFullLog());
    }
}
