package co.jvitoralb.bank;

import co.jvitoralb.printer.Printer;

import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {
    private final Printer printer = new Printer();
    private final String agencia = "001";
    private final List<Account> accountsList = new ArrayList<>();
    private int accountsNumber = 37265;
    private boolean online = false;
    private Account user;
    private int operation;
    private double amount;

    public void createAccount() {
        Scanner read = new Scanner(System.in);
        String nome = read.nextLine();

        String numeroConta = String.valueOf(generateContaNumero());
        this.user = new Account(nome, this.agencia, numeroConta);

        registerAccount();
        printer.printCreateAccountSuccess();
    }

    private int generateContaNumero() {
        accountsNumber++;
        return accountsNumber;
    }

    private void registerAccount() {
        accountsList.add(this.user);
    }

    public void login() {
        String userContaNumero;
        if (this.user != null) {
            userContaNumero = this.user.getNumeroConta();
        } else {
            Scanner read = new Scanner(System.in);
            userContaNumero = read.nextLine();
        }

        for(int i = 0; i < accountsList.size(); i++) {
            Account current = accountsList.get(i);
            if (current.getNumeroConta().equals(userContaNumero)) {
                this.user = current;
                this.online = true;
                break;
            }
        }

        if (this.user == null) {
            printer.printLoginFailed();
        } else {
            printer.printLoginSuccess();
            showMenu();
        }
    }

    public void showMenu() {
        boolean validOperation;

        while(online) {
            printer.printMainMenu(user.getNome(), user.getNumeroConta(), this.agencia);

            validOperation = readOperation();

            if (validOperation) {
                executeOperation();
            }
        }
    }

    private boolean readOperation() {
        try {
            Scanner read = new Scanner(System.in);
            this.operation = read.nextInt();
            return true;
        } catch(InputMismatchException e) {
            printer.printMainMenuUnavailableOperation();
            return false;
        }
    }

    private void executeOperation() {
        switch(operation) {
            case 1:
                readAmount();
                resolveOperation(user.sacar(this.amount));
                break;
            case 2:
                readAmount();
                resolveOperation(user.depositar(this.amount));
                break;
            case 3:
                printer.printSaldo(user.getSaldo());
                break;
            case 4:
                printer.printExtrato(user.getExtrato());
                break;
            case 5:
                logout();
                break;
            default:
                printer.printMainMenuUnavailableOperation();
        }
    }

    private void readAmount() {
        printer.printOperationReadAmount(calculateAction());
        try {
            Scanner read = new Scanner(System.in);
            double amount = read.nextDouble();
            if (amount < 0) {
                throw new InputMismatchException();
            }
            this.amount = amount;
        } catch(InputMismatchException e) {
            printer.printOperationReadAmountFailed();
            readAmount();
        }
    }

    private void resolveOperation(boolean success) {
        String action = calculateAction();
        if (success) {
            user.registerLog(new Log(action, this.amount));
            printer.printResolveOperationSuccess(action, this.amount);
        } else {
            printer.printResolveOperationFailed(action);
        }
    }

    private String calculateAction() {
        if (operation == 1) {
            return "sacar";
        }
        return "depositar";
    }

    private void logout() {
        this.user = null;
        this.online = false;
    }
}
