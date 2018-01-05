package com.xjd.test.kotlin

/**
 *
 *
 * @author elvis.xu
 * @since 2017-11-30 14:49
 */

class Empty

class Test(name: String) {
    var name: String? = name
        get() = field?.toUpperCase();
        set
    var no: Int = 1000
        get() = field
        set(value) {
            if (value < 10) {
                field = value;
            } else {
                field = -1;
            }
        }
    var height: Float = 155.4F
        private set

    init {
        this.name = name + "TTT";
    }
}

fun main(args: Array<String>) {
    val test = Test("XXX")
    println(test.name)

    test.no = 10;
    println("no: ${test.no}")
}
