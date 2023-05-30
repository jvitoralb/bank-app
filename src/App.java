import co.jvitoralb.bank.Bank;
import co.jvitoralb.printer.Printer;

import java.util.Scanner;

public class App {
    static boolean appStatus = true;
    static Bank adalovelace = new Bank();
    static Printer printer = new Printer();

    public static void main(String[] args) {
        printer.printWelcome();

        while(appStatus) {
            printer.printWelcomeMenu();

            Scanner read = new Scanner(System.in);
            String selected = read.nextLine();

            resolveOption(selected);
        }
    }

    private static void resolveOption(String option) {
        switch(option) {
            case "1":
                printer.printCreateAccount();
                adalovelace.createAccount();
                adalovelace.login();
                break;
            case "2":
                printer.printLogin();
                adalovelace.login();
                break;
            case "3":
                closeApplication();
                break;
            default:
                printer.printWelcomeMenuInvalidService();
        }
    }

    private static void closeApplication() {
        appStatus = false;
    }
}
