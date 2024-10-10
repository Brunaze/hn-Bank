// 1.3.3 Creation of the Transfert, Credit, Debit classes

package components;

public class Debit extends Flow {

	public Debit(String comment, double amount, int targetAccountNumber, boolean effect) {
		super(comment, amount, targetAccountNumber, effect);
	}

}
