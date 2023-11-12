package ma.enset;

import ma.enset.enums.AccountStatus;
import ma.enset.enums.AccountType;
import ma.enset.repository.AccountRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AccountRepositoryImpl implements AccountRepository {
    private  static  AccountRepositoryImpl accountRepository;

    //private  static  final AccountRepositoryImpl accountRepository;
////    static {
////        System.out.println("Singleton instantiation");
////        accountRepository=new AccountRepositoryImpl();
////     }
    private AccountRepositoryImpl() {
    }
    //
    private Map<Long,BankAccount> bankAccountMap=new HashMap<>();
    private long accountsCount=0;
    @Override
    public  synchronized BankAccount addAccount(BankAccount bankAccount) {
        Long accountId=++accountsCount;//critical zone
        bankAccount.setAccountId(accountId);
        bankAccountMap.put(accountId,bankAccount);
        return bankAccount;
    }

    @Override
    public Optional<BankAccount> bankAccount(Long accountId) {
       BankAccount bankAccount=bankAccountMap.get(accountId);
        if(bankAccount==null)
       return Optional.empty();
        else
            return Optional.of(bankAccount);
    }
    @Override
    public List<BankAccount> accounts() {

        return bankAccountMap.values().stream().toList();
    }



    @Override
    public List<BankAccount> searchAccounts(Predicate<BankAccount> predicate) {
        return bankAccountMap.values().stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public BankAccount update(BankAccount bankAccount) {
        bankAccountMap.put(bankAccount.getAccountId(),bankAccount);
        return bankAccount;
    }

    @Override
    public void delete(Long accountId) {
          bankAccountMap.remove(accountId);
    }

    @Override
    public void populateData(){
        for (int i=1;i<=10;i++){
            BankAccount bankAccount= BankDirector.accountBuilder()
                     .currency(Math.random()>0.5?"MAD":"USD")
                    .accountStatus(Math.random()>0.5?AccountStatus.CREATED:AccountStatus.ACTIVATED)
                    .accountType(Math.random()>0.5?AccountType.SAVING_ACCOUNT:AccountType.CURRENT_ACCOUNT)
                    .balance(10000+Math.random()*9000)
                    .build();
            addAccount(bankAccount);
        }
        System.out.println("=================================================================");
        System.out.println(Thread.currentThread().getName());
        System.out.println("Account Count = "+accountsCount);
        System.out.println("Size : "+bankAccountMap.values().size());
        System.out.println("=================================================================");

    }

    public  static AccountRepositoryImpl getInstance(){
       if (accountRepository==null){
           System.out.println("Singleton instantiation");
           accountRepository=new AccountRepositoryImpl();
           accountRepository.populateData();
       }

        return accountRepository;
    }


}
