package ma.enset;

import ma.enset.enums.AccountStatus;
import ma.enset.enums.AccountType;

public class Application3 {
    public static void main(String[] args) throws CloneNotSupportedException {
        BankAccount account1=new BankAccount();
        account1.setAccountId(1L);
        account1.setCurrency("MAD");
        account1.setAccountType(AccountType.SAVING_ACCOUNT);
        account1.setAccountStatus(AccountStatus.ACTIVATED);
        account1.setCustomar(new Customar(1L,"Najwa"));

//        BankAccount account2=new BankAccount();
//        account2.setAccountId(account1.getAccountId());
//        account2.setCurrency(account1.getCurrency());
//        account2.setAccountType(account1.getAccountType());
//        account2.setAccountStatus(account1.getAccountStatus());

        System.out.println("Compte 1 : "+account1);

        //Utilisation de pattern prototype

        BankAccount account3=account1.clone();
        System.out.println("Compte 3 : "+ account3);

        System.out.println("=========================");
        account1.getCustomar().setName("Awjan");
        account1.setAccountId(2L);
        System.out.println("Compte 1 : "+account1);
        System.out.println("Compte 3 : "+ account3);


    }
}
