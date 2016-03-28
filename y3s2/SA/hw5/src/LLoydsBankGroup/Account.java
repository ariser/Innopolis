package LLoydsBankGroup;

public abstract class Account {
    protected double balance;
    protected double dailyInterest;
    protected double overdraftMax;
    protected Currency currency;
    protected Customer owner;

    public Account(Customer owner, Currency currency) {
        this(owner, currency, 0, 0, 0);
    }

    public Account(Customer owner, Currency currency, double initialAmount) {
        this(owner, currency, initialAmount, 0, 0);
    }

    public Account(Customer owner, Currency currency, double initialAmount, double dailyInterest) {
        this(owner, currency, initialAmount, dailyInterest, 0);
    }

    public Account(Customer owner, Currency currency, double initialAmount, double dailyInterest, double overdraftMax) {
        this.currency = currency;
        this.balance = initialAmount;
        this.dailyInterest = dailyInterest;
        this.overdraftMax = overdraftMax;
    }

    public void debit(double amount) {
        if (this.balance - amount >= -overdraftMax) {
            this.balance -= amount;
        }
    }

    public void credit(double amount) {
        this.balance += amount;
    }

    protected void applyDailyInterest() {
        balance += dailyInterest;
    }
}
