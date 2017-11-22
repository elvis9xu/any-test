package com.xjd.test.grammar.interfaces;

/**
 * @author elvis.xu
 * @since 2017-10-23 11:46
 */
public interface B {
	default void helloB() {
		System.out.println("HELLO B");
	}

	default void hello() {
		helloB();
	}
}
