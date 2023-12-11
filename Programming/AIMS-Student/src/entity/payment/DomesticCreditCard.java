package entity.payment;

public class DomesticCreditCard extends PaymentCard{
	private String isUsingBank;

	public DomesticCreditCard(String cardCode, String owner, String isUsingBank, String dateExpired) {
		super(cardCode, owner, dateExpired);
		this.isUsingBank = isUsingBank;
	}
}
