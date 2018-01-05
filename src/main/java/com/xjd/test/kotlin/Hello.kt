package com.xjd.test.kotlin

/**
 *
 *
 * @author elvis.xu
 * @since 2017-11-30 11:46
 */

fun sum(a: Int, b: Int): Int {
    return a + b
}

fun printall(vararg v: Int) {
    for (vt in v) {
        println(vt)
    }
}

fun parseInt(str: String?): Int? {
    return str?.toInt();
}

fun getStringLength(obj: Any?): Int? {
//    if (obj !is String) {
//        return obj.toString().length;
//    }
//    return obj?.length;
    return if (obj is String) obj.length else obj?.toString()?.length;
}

fun main(args: Array<String>) {
    println(sum(1, 3))
    printall(1,2,3,4,5)
    val sumLam: (Int, Int) -> Int = {x, y -> x + y}
    println(sumLam(1, 5))

    val a = 1;
    val b: Int = 1
    val c: Int
    c = 1

    var x = 5
    x += 1

    println(x)

    val s = "a is $a"
    val s2 = "${s.replace("is", "was")}, but now is $a"
    println(s2)

    var age: String? = null;
    println(age?.toInt())
    println(age?.toInt() ?: -1)

    val i = parseInt(null)
    println(i)

    println(getStringLength("HAHA"))

    for (i in 1..4) print(i)
    for (i in 1..5 step 2) print(i)
    for (i in 4 downTo 1 step 1) print(i)
    if (i in 1..4) println(i)
    for (i in 1 until 4) print(i)

    println("when")
    when(i) {
        1 -> print("i is 1")
        2 -> print("i is 2")
        null -> print("i is NULL")
        else -> {
            print("i is $i")
        }
    }
    println("when2")
    when {
        i == 1 -> print("i is 1")
        i == null -> print("i is NULL")
        else -> print("i is $i")
    }

    println("for")
    var array = 1..4;
    for (i in array) {
        print(i)
    }
    println("for")
    for ((i, a) in array.withIndex()) {
        println("$i is $a")
    }

    println("break")
    loop@ for (i in 1..100) {
        for (j in 1..100) {
            println("$i -- $j")
            if (j == 10) break@loop;
        }
    }

    println("continue")
    loop@ for (i in 1..10) {
        for (j in 1..10) {
            println("$i -- $j")
            if (j == 2) continue@loop;
        }
    }
}