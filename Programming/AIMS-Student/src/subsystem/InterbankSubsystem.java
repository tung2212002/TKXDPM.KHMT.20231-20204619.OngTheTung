package subsystem;

import common.exception.InternalServerErrorException;
import common.exception.InvalidCardException;
import common.exception.NotEnoughBalanceException;
import entity.payment.CreditCard;
import entity.payment.PaymentCard;
import entity.payment.PaymentTransaction;
import subsystem.interbank.InterbankSubsystemController;

/***
 * The {@code InterbankSubsystem} class is used to communicate with the
 * Interbank to make transaction.
 * 
 * @author hieud
 *
 */
public class InterbankSubsystem implements InterbankInterface {

	/**
	 * Represent the controller of the subsystem
	 */
	private InterbankSubsystemController ctrl;

	/**
	 * Initializes a newly created {@code InterbankSubsystem} object so that it
	 * represents an Interbank subsystem.
	 */
	public InterbankSubsystem() {
		String str = new String();
		this.ctrl = new InterbankSubsystemController();
	}

	/**
	 * @see InterbankInterface#payOrder(entity.payment.PaymentCard, int,
	 *      java.lang.String)
	 */
	public PaymentTransaction payOrder(PaymentCard card, int amount, String contents) {
		PaymentTransaction transaction = ctrl.doTransaction(card, amount, contents, "pay");
		return transaction;
	}

	/**
	 * @see InterbankInterface#refund(entity.payment.PaymentCard, int,
	 *      java.lang.String)
	 */
	public PaymentTransaction refund(PaymentCard card, int amount, String contents) {
		PaymentTransaction transaction = ctrl.doTransaction(card, amount, contents, "refund");
		return transaction;
	}
}
