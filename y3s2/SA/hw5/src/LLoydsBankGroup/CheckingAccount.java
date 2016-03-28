package LLoydsBankGroup;

public class CheckingAccount extends Account {

    /**
     * inv: balance >= 0
     */

    private static final byte maxDebitsInRow = 2;
    private byte debitsInRow = 0;

    public CheckingAccount(Customer owner, Currency currency) {
        super(owner, currency);
    }

    public CheckingAccount(Customer owner, Currency currency, double initialAmount) {
        super(owner, currency, initialAmount);
    }

    /**
     * pre: debitsInRow < maxDebitsInRow
     * post: debitsInRow <= maxDebitsInRow
     */
    @Override
    public void debit(double amount) {
        if (debitsInRow < maxDebitsInRow) {
            super.debit(amount);
            Logger.log(this, "debit", amount);
            debitsInRow++;
        }
    }

    /**
     * post: debitsInRow == 0
     */
    @Override
    public void credit(double amount) {
        super.credit(amount);
        debitsInRow = 0;
        Logger.log(this, "credit", amount);
    }
}
