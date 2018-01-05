package com.xjd.test.kotlin

/**
 *
 *
 * @author elvis.xu
 * @since 2017-11-30 15:58
 */

interface Base {
    fun print() {
        println("base");
    }
}

class BaseImpl: Base {

}

class DelegateBase(b: Base): Base by b {

    fun other() {
        println("other")
    }
}

fun main(args: Array<String>) {
    val b = BaseImpl()
    val d = DelegateBase(b);
    d.print()
    d.other()
}