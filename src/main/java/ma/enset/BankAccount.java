package ma.enset;

import ma.enset.enums.AccountStatus;
import ma.enset.enums.AccountType;

public class BankAccount implements Cloneable{
    private Long accountId;
    private double balance;
    private String currency;

    private AccountType accountType;

    private AccountStatus accountStatus;

    private  Customar customar;
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", accountType=" + accountType +
                ", accountStatus=" + accountStatus +
                '}';
    }
    //Pattern Builder
    public static class AccountBuilder{
        private BankAccount bankAccount=new BankAccount();

        public AccountBuilder accountId(Long id){
            bankAccount.accountId=id;
            return this;
        }
        public AccountBuilder currency(String currency){
            bankAccount.currency=currency;
            return this;
        }
        public AccountBuilder accountType(AccountType accountType){
            bankAccount.accountType=accountType;
            return this;
        }
        public AccountBuilder accountStatus(AccountStatus accountStatus){
//            if (bankAccount.getAccountType().equals(AccountType.CURRENT_ACCOUNT))
            bankAccount.accountStatus=accountStatus;
//            else
//                bankAccount.accountStatus=AccountStatus.ACTIVATED;
            return this;
        }
        public AccountBuilder balance(double balance){
            bankAccount.balance=balance;
            return this;
        }

        public  BankAccount build(){
            return this.bankAccount;
        }
    }

    @Override
    public BankAccount clone() throws CloneNotSupportedException {
        return (BankAccount) super.clone();
    }
}
