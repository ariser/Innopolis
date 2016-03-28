package LLoydsBankGroup;

public class SavingAccount extends Account {
    public SavingAccount(Customer owner, Currency currency) {
        super(owner, currency);
    }

    public SavingAccount(Customer owner, Currency currency, double initialAmount) {
        super(owner, currency, initialAmount);
    }

    public SavingAccount(Customer owner, Currency currency, double initialAmount, double dailyInterest) {
        super(owner, currency, initialAmount, dailyInterest);
    }

    public SavingAccount(Customer owner, Currency currency, double initialAmount, double dailyInterest, double overdraftMax) {
        super(owner, currency, initialAmount, dailyInterest, overdraftMax);
    }

    /**
     * balance + overdraftMax >= amount
     */
    @Override
    public void debit(double amount) {
        super.debit(amount);
        Logger.log(this, "debit", amount);
    }

    @Override
    public void credit(double amount) {
        super.credit(amount);
        Logger.log(this, "credit", amount);
    }
}
