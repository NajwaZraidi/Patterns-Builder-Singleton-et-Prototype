package ma.enset;

import ma.enset.enums.AccountType;
import ma.enset.repository.AccountRepository;
import ma.enset.util.JsonSerializer;

import java.io.IOException;
import java.util.List;

public class Application2 {
    public static void main(String[] args) throws IOException {

        System.out.println(Thread.currentThread().getName());

        JsonSerializer<BankAccount> bankAccountJsonSerializer=new JsonSerializer<>();

        //Instantiation une seul fois
       AccountRepository accountRepository=AccountRepositoryImpl.getInstance();
       accountRepository.populateData();
       for (int i=0;i<10;i++){
           new Thread(()->{
               accountRepository.populateData();
           }).start();
       }
        System.out.println("Tap a character : ");
       System.in.read();
       List<BankAccount> bankAccounts=accountRepository.accounts();
        // Affichage format Json
        bankAccounts.stream()
                .map(bankAccountJsonSerializer :: toJson)
                .forEach(System.out::println);


    }
}