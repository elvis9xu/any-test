package com.xjd.test.grammar.interfaces;

/**
 * @author elvis.xu
 * @since 2017-10-23 11:46
 */
public interface A {
	default void helloA() {
		System.out.println("HELLO A");
	}

	default void hello() {
		helloA();
	}
}
