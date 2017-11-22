package com.xjd.test.grammar.interfaces;

/**
 * @author elvis.xu
 * @since 2017-10-23 11:50
 */
public interface AB extends A, B {
	@Override
	default void hello() {

	}
}
