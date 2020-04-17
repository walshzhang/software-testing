package iteach.softwaretesting.junit5;

import java.util.Objects;

public final class Money {
	private final String currency;
	private final int amount;

	public Money(String currency, int amount) {
		if (amount < 0) {
			throw new NegativeMoneyAmountException();
		}
		this.amount = amount;
		this.currency = currency;
	}

	public static Money dollar(int amount) {
		return new Money("USD", amount);
	}

	public static Money renminbi(int amount) {
		return new Money("RMB", amount);
	}

	public Money add(Money money) {
		if (money.currency.equals(this.currency)) {
			return new Money(this.currency, amount + money.amount);
		}
		throw new BadCurrencyException(money.currency);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Money money = (Money) o;
		return Double.compare(money.amount, amount) == 0 && Objects.equals(currency, money.currency);
	}

	@Override
	public int hashCode() {
		return Objects.hash(currency, amount);
	}

	@Override
	public String toString() {
		return "Money{" +
				"currency='" + currency + '\'' +
				", amount=" + amount +
				'}';
	}
}
