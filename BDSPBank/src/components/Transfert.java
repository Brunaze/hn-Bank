// 1.3.3 Creation of the Transfert, Credit, Debit classes

package components;

public class Transfert extends Flow {

	private int transferAccountNumber;
	
	public Transfert() {
        super();
    }

	public Transfert(String comment, double amount, int targetAccountNumber, int transferAccountNumber, boolean effect) {
		super(comment, amount, targetAccountNumber, effect);
		this.transferAccountNumber = transferAccountNumber;
	}

	public int getTransferAccountNumber() {
		return transferAccountNumber;
	}

	public void setTransferAccountNumber(int transferAccountNumber) {
		this.transferAccountNumber = transferAccountNumber;
	}
	
	

}
