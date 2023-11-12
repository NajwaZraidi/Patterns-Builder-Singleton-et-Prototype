package ma.enset.repository;

import ma.enset.BankAccount;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface AccountRepository {

    BankAccount addAccount(BankAccount bankAccount);
    List<BankAccount> accounts();

     Optional<BankAccount> bankAccount(Long accountId);
     List<BankAccount> searchAccounts(Predicate<BankAccount> predicate);
    BankAccount update(BankAccount bankAccount);
    void delete(Long accountId);
     void populateData();
}
