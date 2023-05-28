package co.jvitoralb.bank;

import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {
    private final String agencia = "001";
    private final List<Account> accountsList = new ArrayList<>();
    private int accountsNumber = 37265;
    private boolean online = false;
    private Account user;
    private int operation;
    private double amount;

    public void createAccount() {
        System.out.printf("%n~~~~~ CRIAR CONTA ~~~~~%n");
        System.out.printf("%nQual o seu nome?%n");

        Scanner read = new Scanner(System.in);
        String nome = read.nextLine();

        String numeroConta = String.valueOf(generateContaNumero());
        Account newUser = new Account(nome, this.agencia, numeroConta);

        insertAccount(newUser);
        System.out.printf("%nConta criada com sucesso!%n");

        login(newUser.getNumeroConta());
    }

    private int generateContaNumero() {
        accountsNumber++;
        return accountsNumber;
    }

    private void insertAccount(Account newUser) {
        accountsList.add(newUser);
    }

    public void login(String userContaNumero) {
        for(int i = 0; i < accountsList.size(); i++) {
            Account current = accountsList.get(i);
            if (current.getNumeroConta().equals(userContaNumero)) {
                this.user = current;
                this.online = true;
                break;
            }
        }

        if (this.user == null) {
            System.out.printf("%n~~~~~~~~~~~~~~~~~~~~");
            System.out.printf("%n~~ Conta inválida ~~");
            System.out.printf("%n~~~~~~~~~~~~~~~~~~~~%n");
        } else {
            showMenu();
        }
    }

    public void showMenu() {
        boolean validOperation;

        while(online) {
            System.out.printf("%n~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.printf("%nOlá, %s!%nConta: %s | Agência: %s", user.getNome(), user.getNumeroConta(), this.agencia);
            System.out.printf("%n~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.printf("%nEssas são as operações disponíveis:%n");
            System.out.printf("1 - Sacar%n2 - Depositar%n3 - Saldo%n4 - Extrato%n5 - Sair%n");
            System.out.println("O que voce deseja fazer?");

            validOperation = readOperation();

            if (validOperation) {
                executeOperation();
            }
        }
    }

    private boolean readOperation() {
        Scanner read = new Scanner(System.in);
        try {
            this.operation = read.nextInt();
            return true;
        } catch(InputMismatchException e) {
            System.out.printf("%nOperação inválida! Digite apenas números.%n");
            return false;
        }
    }

    private void executeOperation() {
        boolean result;

        switch(operation) {
            case 1:
                readAmount();
                result = user.sacar(this.amount);
                resolve(result);
                break;
            case 2:
                readAmount();
                result = user.depositar(this.amount);
                resolve(result);
                break;
            case 3:
                user.getSaldo();
                break;
            case 4:
                user.getExtrato();
                break;
            case 5:
                exit();
                break;
            default:
                System.out.printf("%nOperação não disponível.%n");
        }
    }

    private void readAmount() {
        String action = (operation == 1) ? "sacar" : "depositar";

        System.out.printf("%n~~~~~~~~ %s ~~~~~~~~%n", action.toUpperCase());
        System.out.printf("%nQuanto deseja %s?%n", action);

        Scanner read = new Scanner(System.in);
        this.amount = read.nextDouble();
    }

    private void resolve(boolean success) {
        String action = (operation == 1) ? "Saque" : "Depósito";

        if (success) {
            user.registerLog(new Log(action, this.amount));
            System.out.printf("%n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~%n");
            System.out.printf("%n%s de %.2f realizado com sucesso!%n", action, this.amount);
            System.out.printf("%n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~%n");
        } else {
            System.out.printf("%n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.printf("%nNão foi possível realizar o %s!", action);
            if (operation == 1) System.out.printf("%nSaldo insuficiente.");
            System.out.printf("%n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~%n");
        }
    }

    private void exit() {
        this.online = false;
    }
}
