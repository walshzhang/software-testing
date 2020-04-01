package iteach.softwaretesting.junit5;

public class BadCurrencyException extends RuntimeException {
	public BadCurrencyException(String currency) {
		super(String.format("非法的货币类型：%s", currency));
	}
}
