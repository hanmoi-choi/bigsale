package springbook.learningtest.template;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CalcSumTest {
	Calculator calculator;
	String numFilepath;
	
	@Before public void setUp() {
		this.calculator = new Calculator();
		this.numFilepath = "/Users/hanmoi/git/bigsale/src/main/java/springbook/learningtest/template/numbers.txt";
	}
	
	@Test public void sumOfNumbers() throws IOException {
		assertThat(calculator.calcSum(this.numFilepath), is(10));
	}
	
	@Test public void multiplyOfNumbers() throws IOException {
		assertThat(calculator.calcMultiply(this.numFilepath), is(24));
	}
	
	@Test public void concatenateStrings() throws IOException {
		assertThat(calculator.concatenate(this.numFilepath), is("1234"));
	}

}

