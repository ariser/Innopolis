package LLoydsBankGroup;

public class Professor extends Customer {
    private static final double initialSavingAccountBalance = 200;

    public Professor(String firstName, String lastName) {
        super(firstName, lastName, Currency.EUR);
    }

    @Override
    public void initAccounts(Currency accountsCurrency) {
        super.initAccounts(accountsCurrency);
        getSavingAccount().credit(initialSavingAccountBalance);
    }
}
