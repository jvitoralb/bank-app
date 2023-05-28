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

    public void getSaldo() {
        System.out.printf("%nSeu saldo atual é de %.2f %n", this.saldo);
    }

    public void getExtrato() {
        if (logs.isEmpty()) {
            System.out.printf("%nNenhuma operação realizada.%n");
            return;
        }
        for(int i = 0; i < logs.size(); i++) {
            System.out.printf("%n%s%n", logs.get(i));
        }
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

    @Override
    public String toString() {
        return "Account {" +
                "nome: " + nome + "," +
                "agencia: " + agencia + "," +
                "conta_numero: " + numeroConta + "," +
                "saldo: " + saldo + "," +
                "logs: " + logs +
                "}";
    }
}
