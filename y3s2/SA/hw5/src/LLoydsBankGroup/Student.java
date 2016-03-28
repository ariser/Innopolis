package LLoydsBankGroup;

public class Student extends Customer {
    private static final double initialSavingAccountBalance = 100;

    public Student(String firstName, String lastName) {
        super(firstName, lastName, Currency.EUR);
    }

    @Override
    public void initAccounts(Currency accountsCurrency) {
        super.initAccounts(accountsCurrency);
        getSavingAccount().credit(initialSavingAccountBalance);
    }
}
