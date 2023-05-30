package co.jvitoralb.printer;

import java.util.List;

public class Printer {
    public void printWelcome() {
        System.out.printf("%n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.printf("%n~~~~~ Banco AdaLovelace! ~~~~~");
        System.out.printf("%n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~%n");
    }
    public void printWelcomeMenu() {
        System.out.printf("%nSeja Bem-vinda(o)!%n");
        System.out.printf("%nServiços disponíveis: %n1 - Criar conta%n2 - Acessar conta%n3 - Sair do programa%n");
        System.out.printf("%nComo posso ajudar?%n");
    }
    public void printWelcomeMenuInvalidOption() {
        System.out.printf("%nOpção inválida.%n");
    }
    public void printLogin() {
        System.out.printf("%n~~~~~ ACESSAR CONTA ~~~~~%n");
        System.out.printf("%nQual o número da conta?%n");
    }
    public void printLoginFailed() {
        System.out.printf("%n~~~~~~~~~~~~~~~~~~~~");
        System.out.printf("%n~~ Conta inválida ~~");
        System.out.printf("%n~~~~~~~~~~~~~~~~~~~~%n");
    }
    public void printLoginSuccess() {
        System.out.printf("%nLogado com sucesso!%n");
    }
    public void printCreateAccount() {
        System.out.printf("%n~~~~~ CRIAR CONTA ~~~~~%n");
        System.out.printf("%nQual o seu nome?%n");
    }
    public void printCreateAccountSuccess() {
        System.out.printf("%nConta criada com sucesso!%n");
    }
    public void printMainMenu(String userNome, String userNumeroConta, String agencia) {
        System.out.printf("%n~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.printf("%nOlá, %s!%nConta: %s | Agência: %s", userNome, userNumeroConta, agencia);
        System.out.printf("%n~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.printf("%nEssas são as operações disponíveis:%n");
        System.out.printf("1 - Sacar%n2 - Depositar%n3 - Saldo%n4 - Extrato%n5 - Sair%n");
        System.out.println("O que voce deseja fazer?");
    }
    public void printMainMenuUnavailableOperation() {
        System.out.printf("%nOperação não disponível.%n");
    }
    public void printOperationReadAmount(String action) {
        System.out.printf("%n~~~~~~~~ %s ~~~~~~~~%n", action.toUpperCase());
        System.out.printf("%nQuanto deseja %s?%n", action);
    }
    public void printResolveOperationSuccess(String action, double amount) {
        System.out.printf("%n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~%n");
        System.out.printf("%n%s de %.2f realizado com sucesso!%n", action, amount);
        System.out.printf("%n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~%n");
    }
    public void printResolveOperationFailed(String action) {
        System.out.printf("%n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.printf("%nNão foi possível realizar o %s!", action);
        if (action.equals("Saque")) System.out.printf("%nSaldo insuficiente.");
        System.out.printf("%n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~%n");
    }
    public void printSaldo(double saldo) {
        System.out.printf("%n~~~~~~~~~~~~~~ SALDO ~~~~~~~~~~~~~~%n");
        System.out.printf("%nSeu saldo atual é de %.2f %n", saldo);
        System.out.printf("%n~~~~~~~~~~~~~~ SALDO ~~~~~~~~~~~~~~%n");
    }
    public void printExtrato(List<String> extrato) {
        System.out.printf("%n~~~~~~~~~~~~~~~~~~~~~~~ EXTRATO ~~~~~~~~~~~~~~~~~~~~~~~%n");
        if (extrato.isEmpty()) {
            System.out.printf("%nNenhuma operação realizada.%n");
        } else {
            for(int i = 0; i < extrato.size(); i++) {
                System.out.printf("%n%s%n", extrato.get(i));
            }
        }
        System.out.printf("%n~~~~~~~~~~~~~~~~~~~~~~~ EXTRATO ~~~~~~~~~~~~~~~~~~~~~~~%n");
    }
}
