package ma.enset;

import ma.enset.enums.AccountType;
import ma.enset.repository.AccountRepository;
import ma.enset.util.JsonSerializer;

import java.util.List;

public class Application1 {
    public static void main(String[] args) {

        //Implimentation de pattern Builder
//        BankAccount bankAccount= BankDirector.accountBuilder()
//                .accountId(1L)
//                .currency("MAD")
//                .accountStatus(AccountStatus.CREATED)
//                .accountType(AccountType.SAVING_ACCOUNT)
//                .balance(700000)
//                .build();
//        BankAccount bankAccount=BankAccount.builder()
//                .accountId(1L)
//                .currency("MAD")
//                .accountStatus(AccountStatus.CREATED)
//                .accountType(AccountType.SAVING_ACCOUNT)
//                .balance(700000)
//                .build();
//        System.out.println(bankAccount.toString());
        JsonSerializer<BankAccount> bankAccountJsonSerializer=new JsonSerializer<>();
      //Instantiation une seul fois
       AccountRepository accountRepository=AccountRepositoryImpl.getInstance();
       AccountRepository accountRepository2=AccountRepositoryImpl.getInstance();
       AccountRepository accountRepository3=AccountRepositoryImpl.getInstance();
       //accountRepository.populateData();
       List<BankAccount> bankAccounts=accountRepository.accounts();
       //bankAccounts.forEach(System.out::println);
       // Affichage format Json
        bankAccounts.stream()
                .map(bankAccountJsonSerializer :: toJson)
                .forEach(System.out::println);
        System.out.println("*********************************************");
        BankAccount account=accountRepository.bankAccount(1L).orElse(null);
        if (account!=null){
            System.out.println(bankAccountJsonSerializer.toJson(account));
        }
        System.out.println("=============================================");
        List<BankAccount> bankAccountList=accountRepository.searchAccounts(bankAccount -> bankAccount.getAccountType().equals(AccountType.CURRENT_ACCOUNT) && bankAccount.getBalance()>10000);
        bankAccountList.stream()
                .map(bankAccountJsonSerializer :: toJson)
                .forEach(System.out::println);

    }
}