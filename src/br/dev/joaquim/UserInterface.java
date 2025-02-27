package br.dev.joaquim;

import java.util.Random;
import java.util.Scanner;
import br.dev.joaquim.bank.BankAccount;
import br.dev.joaquim.bank.InsufficientFundsException;

public class UserInterface {
    private Scanner input = new Scanner(System.in);
    private BankAccount account;

    private void welcome() {
        System.out.println("Bem-vindo ao sistema bancário");
        System.out.print("Vamos criar sua conta, informe seu nome: ");
        String holderName = input.nextLine();
        int accountNumber = 1000 + (new Random()).nextInt(8999);
        System.out.println("Criamos uma conta com o número: " + accountNumber + ", com saldo igual a 0 (zero).");
        this.account = new BankAccount(accountNumber, 0, holderName);
    }

    private void showMenu() {
        System.out.println("\n\n-----------------------");
        System.out.println("Escolha uma das opções:");
        System.out.println("\t1. Verificar dados da conta.");
        System.out.println("\t2. Depositar.");
        System.out.println("\t3. Sacar.");
        System.out.println("\t4. Sair.");
        System.out.print("Opção > ");
    }

    public void start() {
        welcome();
        if (account == null)
            return;

        while (true) {
            showMenu();
            try {
                int choice = readOption();
                
                // Verificação para ver se a opção está dentro do intervalo desejado
                if (choice < 1 || choice > 4) {
                    System.out.println("Opção inválida. Escolha um número entre 1 e 4.");
                    continue; 
                }
                switch (choice) {
                    case 1:
                        System.out.println("\n" + this.account);
                        break;
                    case 2:
                        deposit();
                        break;
                    case 3:
                        withdraw();
                        break;
                    case 4:
                        System.out.println("Até a próxima.");
                        return;
                }
                waitUser();
            } catch (NumberFormatException ex) {
                System.out.println("Valor informado não é um número");
            }
        }
    }

    private void deposit() {
        System.out.print("\nInforme o valor a ser depositado: ");
        double value = readValue();
        account.deposit(value);
        System.out.println("Depósito realizado com sucesso.");
    }
    
    /**
     * Solicita ao usuário o valor a ser sacado e tenta realizar o saque.
     * Se o saldo for insuficiente, exibe uma mensagem de erro.
     */
    private void withdraw() {
        System.out.print("\nInforme o valor a ser sacado: ");
        double value = readValue();
        try {
            account.withdraw(value);
            System.out.println("Saque realizado com sucesso");
        } catch (InsufficientFundsException e) {
            System.out.println("Erro: Não há saldo suficiente para o saque de: " + value + " Reais");
        }
    }

    private int readOption() {
        String choiceString = input.nextLine();
        return Integer.parseInt(choiceString);
    }

    private double readValue() {
        String line = input.nextLine();
        return Double.parseDouble(line);
    }

    private void waitUser() {
        System.out.println("Pressione ENTER para continuar...");
        input.nextLine();
    }
}
