package group06.subsystem.interbank;

import java.util.Map;

import group06.common.exception.InternalServerErrorException;
import group06.common.exception.InvalidCardException;
import group06.common.exception.InvalidTransactionAmountException;
import group06.common.exception.InvalidVersionException;
import group06.common.exception.NotEnoughBalanceException;
import group06.common.exception.NotEnoughTransactionInfoException;
import group06.common.exception.SuspiciousTransactionException;
import group06.common.exception.UnrecognizedException;
import group06.entity.payment.CreditCard;
import group06.entity.payment.PaymentTransaction;
import group06.utils.Configs;
import group06.utils.MyMap;
import group06.utils.Utils;

public class InterbankSubsystemController {

	private static final String PUBLIC_KEY = "AQzdE8O/fR8=";
	private static final String SECRET_KEY = "BUXj/7/gHHI=";
	private static final String PAY_COMMAND = "pay";
	private static final String VERSION = "1.0.0";

	private static InterbankBoundary interbankBoundary = new InterbankBoundary();

	//Logical cohesion
	public PaymentTransaction refund(CreditCard card, int amount, String contents) {
		return null;
	}
	
	//Sequential cohesion do là output của hàm interbankBoundary.query
	private String generateData(Map<String, Object> data) {
		return ((MyMap) data).toJSON();
	}

	//Sequential cohesion do là output cho hàm payOrder
	public PaymentTransaction payOrder(CreditCard card, int amount, String contents) {
		Map<String, Object> transaction = new MyMap();

		try {
			transaction.putAll(MyMap.toMyMap(card));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new InvalidCardException();
		}
		transaction.put("command", PAY_COMMAND);
		transaction.put("transactionContent", contents);
		transaction.put("amount", amount);
		transaction.put("createdAt", Utils.getToday());

		Map<String, Object> requestMap = new MyMap();
		requestMap.put("version", VERSION);
		requestMap.put("transaction", transaction);

		String responseText = interbankBoundary.query(Configs.PROCESS_TRANSACTION_URL, generateData(requestMap));
		MyMap response = null;
		try {
			response = MyMap.toMyMap(responseText, 0);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new UnrecognizedException();
		}

		return makePaymentTransaction(response);
	}

	//Sequential cohesion do lấy input từ hàm payOrder
	private PaymentTransaction makePaymentTransaction(MyMap response) {
		if (response == null)
			return null;
		MyMap transcation = (MyMap) response.get("transaction");
		CreditCard card = new CreditCard((String) transcation.get("cardCode"), (String) transcation.get("owner"),
				Integer.parseInt((String) transcation.get("cvvCode")), (String) transcation.get("dateExpired"));
		PaymentTransaction trans = new PaymentTransaction((String) response.get("errorCode"), card,
				(String) transcation.get("transactionId"), (String) transcation.get("transactionContent"),
				Integer.parseInt((String) transcation.get("amount")), (String) transcation.get("createdAt"));

		switch (trans.getErrorCode()) {
		case "00":
			break;
		case "01":
			throw new InvalidCardException();
		case "02":
			throw new NotEnoughBalanceException();
		case "03":
			throw new InternalServerErrorException();
		case "04":
			throw new SuspiciousTransactionException();
		case "05":
			throw new NotEnoughTransactionInfoException();
		case "06":
			throw new InvalidVersionException();
		case "07":
			throw new InvalidTransactionAmountException();
		default:
			throw new UnrecognizedException();
		}

		return trans;
	}

}
