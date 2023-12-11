package entity.payment;

public abstract class PaymentCard {
  private String cardCode;
	private String owner;
	private String dateExpired;

	public PaymentCard(String cardCode, String owner,  String dateExpired) {
		this.cardCode = cardCode;
		this.owner = owner;
		this.dateExpired = dateExpired;
	}
}
