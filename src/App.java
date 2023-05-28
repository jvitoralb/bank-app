import co.jvitoralb.bank.Bank;

import java.util.Scanner;

public class App {
    static boolean appStatus = true;

    public static void main(String[] args) {
        System.out.printf("%n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.printf("%n~~~~~ Banco AdaLovelace! ~~~~~");
        System.out.printf("%n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~%n");
        Bank adalovelace = new Bank();

        while(appStatus) {
            System.out.printf("%nSeja Bem-vinda(o)!%n");
            System.out.printf("%nServiços disponíveis: %n1 - Criar conta%n2 - Acessar conta%n3 - Sair do programa%n");
            System.out.printf("%nComo posso ajudar?%n");

            Scanner read = new Scanner(System.in);
            String selected = read.nextLine();

            resolveOption(selected, adalovelace);
        }
    }

    private static void resolveOption(String option, Bank adalovelace) {
        switch(option) {
            case "1":
                adalovelace.createAccount();
                break;
            case "2":
                String numeroConta = readNumeroConta();
                adalovelace.login(numeroConta);
                break;
            case "3":
                closeApplication();
                break;
            default:
                System.out.printf("%nOpção inválida.");
        }
    }

    private static String readNumeroConta() {
        System.out.printf("%n~~~~~ ACESSAR CONTA ~~~~~%n");
        System.out.printf("%nQual o número da conta?%n");

        Scanner readNumeroConta = new Scanner(System.in);
        return readNumeroConta.nextLine();
    }

    private static void closeApplication() {
        appStatus = false;
    }
}
