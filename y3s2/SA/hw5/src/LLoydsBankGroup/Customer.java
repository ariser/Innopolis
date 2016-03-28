package LLoydsBankGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class Customer {

    /**
     * inv: accounts.size() == 2
     */

    private static final int accountsNumber = 2;

    protected String firstName;
    protected String lastName;

    protected List<Account> accounts = new ArrayList<>(accountsNumber);

    public Customer(String firstName, String lastName, Currency accountsCurrency) {
        this.firstName = firstName;
        this.lastName = lastName;
        initAccounts(accountsCurrency);
    }

    protected void initAccounts(Currency accountsCurrency) {
        CheckingAccount chAccount = new CheckingAccount(this, accountsCurrency);
        SavingAccount svAccount = new SavingAccount(this, accountsCurrency);
        accounts.add(svAccount);
        accounts.add(chAccount);
    }

    protected CheckingAccount getCheckingAccount() {
        for (Account account : accounts) {
            if (account instanceof CheckingAccount) {
                return (CheckingAccount) account;
            }
        }
        return null;
    }

    protected SavingAccount getSavingAccount() {
        for (Account account : accounts) {
            if (account instanceof SavingAccount) {
                return (SavingAccount) account;
            }
        }
        return null;
    }
}
