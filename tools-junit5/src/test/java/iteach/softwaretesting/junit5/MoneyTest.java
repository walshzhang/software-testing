package iteach.softwaretesting.junit5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

@DisplayName("测试 Money 相关的用例")
class MoneyTest {
	Money dollar5 = null;
	Money dollar6 = null;
	
	@BeforeAll
	static void initAll() {
		// 建立数据库连接
		// 设置环境变量
		// 设置用户名密码
	}
	
	@AfterAll
	static void cleanAll() {
		// 释放连接
		// 撤销环境变量
	}
	
	@BeforeEach
	void init() {
		dollar5 = Money.dollar(5);
		dollar6 = Money.dollar(6);
	}
	
	@AfterEach
	void clean() {
		// 清理环境，删除临时文件
	}
		
	// 测试需求是什么？
	// 测试相同的货币，相同的金额的两个对象是相等的
	@Test
	@Order(1)
	@EnabledOnOs(OS.LINUX)
	@DisplayName("测试相同的货币，相同的金额的两个对象是相等的")
	void test_equality_with_same_currency_and_amount() {
		Money anotherDollar5 = Money.dollar(5);

		boolean actual = dollar5.equals(anotherDollar5);
		assertTrue(actual);
		assertEquals(true, actual);

		assertNotSame(dollar5, anotherDollar5);
	}

	// 测试相同的货币，不同的金额的两个对象是不相等的
	@Test
	@Order(2)
	@DisplayName("测试相同的货币，不同的金额的两个对象是不相等的")
	void test_equality_with_different_currency_and_amount() {
		boolean actual = dollar5.equals(dollar6);
		assertFalse(actual);
		assertNotEquals(true, actual);

		assertNull(null);
		assertNotNull(dollar5);
	}

	// 测试在构造货币对象时，如果金额小于0，将抛出异常
	@Test
	@Order(4)
	@DisplayName("测试在构造货币对象时，如果金额小于0，将抛出异常")
	void test_negative_amount_exception_in_constructor() {
		Executable excutable = () -> Money.dollar(-5);
		assertThrows(NegativeMoneyAmountException.class, excutable);
	}
	
	// 测试add 正常的行为
	@Test
	@Order(3)
	@DisplayName("测试add 正常的行为")
	void test_add() {		
		Money dollar11 = Money.dollar(11);
		
		assertEquals(dollar11, dollar5.add(dollar6));
	}
	
	// 测试在进行 add 操作时，如果货币类型不同，也将抛出异常
	// 略！
	
	@ParameterizedTest 
	@DisplayName("使用同一个测试方法对四套数据对 Money 进行相等性测试")
	@MethodSource("moneyProvider") 
	void test_equality(Money m1, Money m2, boolean equality) {
	  assertEquals(equality, m1.equals(m2));
	}
	
	static Stream<Arguments> moneyProvider() {
		 return Stream.of(
				    Arguments.of(Money.dollar(5), Money.dollar(5), true), 
				    Arguments.of(Money.dollar(5), Money.dollar(6), false),
				    Arguments.of(Money.renminbi(5), Money.dollar(5), false),
				    Arguments.of(Money.renminbi(5), Money.dollar(6), false));
	}
}
