package br.dev.joaquim.bank;


public class BankAccount {
  private int accountNumber;
  private double balance;
  private String accountHolderName;

  public BankAccount() {
  }

  public BankAccount(int accountNumber, double balance, String accountHolderName) {
      this.accountNumber = accountNumber;
      this.balance = balance;
      this.accountHolderName = accountHolderName;
  }

  public int getAccountNumber() {
      return accountNumber;
  }

  public double getBalance() {
      return balance;
  }

  public String getAccountHolderName() {
      return accountHolderName;
  }

  public void deposit(double value) {
      if (value <= 0) {
          throw new IllegalArgumentException("O valor precisa ser positivo, foi informado R$ " + value);
      }
      this.balance += value;
  }
  /**
   * @param value O valor a ser sacado. Deve ser um número positivo. 
   * @throws InsufficientFundsException Se o valor for menor ou igual a zero e caso o saldo disponível for insuficiente para o saque.
   */
  public void withdraw(double value) throws InsufficientFundsException {
      if (value <= 0) {
          throw new IllegalArgumentException("O valor precisa ser positivo, foi informado R$ " + value);
      }

      if (value > this.balance) {
        throw new InsufficientFundsException("Saldo insuficiente: tentativa de sacar R$ " + value + " com saldo de R$ " + this.balance);
      }

      this.balance -= value;
  }

  @Override
  public String toString() {
      return "Conta " + accountNumber + " de " + accountHolderName + " tem R$ " + balance + " de saldo";
  }
}
