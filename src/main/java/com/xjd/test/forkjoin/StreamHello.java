package com.xjd.test.forkjoin;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.netty.util.internal.ConcurrentSet;
import lombok.Getter;
import lombok.ToString;

/**
 * @author elvis.xu
 * @since 2017-05-11 13:51
 */
public class StreamHello {
	public static void main(String[] args) {
		// 生成 0 3 6 9 ... 135 数列并求和
		Integer reduce = Stream.iterate(0, n -> n + 3).limit(10).peek(e -> System.out.println(e)).reduce(0, (x, y) -> x + y);
		System.out.println(reduce);
		// 生成26个字母的数列并打印
		String s = Stream.iterate('A', n -> (char) ((int) n + 1)).limit(26).map(x -> "" + x).reduce("", (x, y) -> x + y);
		System.out.println(s);
		// 生成0-9的数列并连接打印
		String c = Stream.iterate(0, n -> n + 1).limit(10).map(String::valueOf).collect(Collectors.joining(","));
		System.out.println(c);
		// 拼接0-9和A-Z的数列并打印成JSON数组方式
		String c2 = Stream.of(1, 2).flatMap(x -> x == 1 ? Stream.iterate(0, n -> n + 1).limit(10) : Stream.iterate('A', n -> (char) (n + 1)).limit(26)).map(String::valueOf).collect(Collectors.joining("\",\"", "[\"", "\"]"));
		System.out.println(c2);
		// 拆解|a|b|c|结构成列表后又封装成该结构
		String c3 = Arrays.stream("|1|2|3|4|5|6|".split("\\|")).filter(e -> !"".equals(e.trim())).collect(Collectors.joining("|", "|", "|"));
		System.out.println(c3);
		// 对象按某属性分组
		Map<Integer, List<Person>> m = Stream.of(18, 28, 38).flatMap(x -> Stream.iterate(1, n -> n + 1).limit(3).map(y -> new Person("XXX" + y, x))).collect(Collectors.groupingBy(p -> p.getAge()));
		m.entrySet().forEach(e -> {
			System.out.println(e.getKey() + ":");
			e.getValue().stream().forEach(v -> System.out.println("\t" + v));
		});
		// 字符去重1 (hashcode + equals)
		String d1 = Stream.iterate(1, n -> n + 1).limit(2).flatMap(x -> Stream.iterate('A', n -> (char) ((int) n + 1)).limit(26)).distinct().map(e -> e + "").collect(Collectors.joining(",", "[", "]"));
		System.out.println(d1);
		// 对象根据某一属性去重
		String d2 = Stream.iterate(1, n -> n + 1).limit(2).flatMap(n -> Stream.iterate(1, x -> x + 1).limit(2)).map(n -> new Person("NAME_" + n, n + 20)).filter(
				new Function<Function<Person, String>, Predicate<Person>>() {
					@Override
					public Predicate<Person> apply(Function<Person, String> keyExtractor) {
						Set<String> set = new ConcurrentSet<>();
						return t -> set.add(keyExtractor.apply(t));
					}
				}.apply(p -> p.getName())
		).map(e -> e.toString()).collect(Collectors.joining(",", "[", "]"));
		System.out.println(d2);
		// 对象根据某一属性去重
		Set<String> set = new ConcurrentSet<>();
		String d3 = Stream.iterate(1, n -> n + 1).limit(2).flatMap(n -> Stream.iterate(1, x -> x + 1).limit(2)).map(n -> new Person("NAME_" + n, n + 20)).filter(
				((Function<Function<Person, String>, Predicate<Person>>) (ext) -> t -> set.add(ext.apply(t))).apply(p -> p.getName())
		).map(e -> e.toString()).collect(Collectors.joining(",", "[", "]"));
		System.out.println("d3: " + d3);
		// 对象根据某一属性去重
		String d4 = Stream.iterate(1, n -> n + 1).limit(2).flatMap(n -> Stream.iterate(1, x -> x + 1).limit(2)).map(n -> new Person("NAME_" + n, n + 20)).filter(
				((Function<Function<Person, String>, Predicate<Person>>) (ext) -> {
					Set<String> set2 = new ConcurrentSet<>();
					return t -> set2.add(ext.apply(t));
				}).apply(p -> p.getName())
		).map(e -> e.toString()).collect(Collectors.joining(",", "[", "]"));
		System.out.println("d4: " + d4);
		// 生成指定长度的随机数
		Object g1 = Stream.generate(((Supplier<Supplier>) () -> {
			Random random = new Random();
			return () -> random.nextInt(10);
		}).get()).limit(5).map(String::valueOf).collect(Collectors.joining());
		System.out.println("g1: " + g1);
		// Function 使用
		Function<String, String> func = fs -> "'" + fs.toUpperCase() + "'";
		Function<String, String> func2 = fs -> "[" + func.apply(fs) + "]";
		BiFunction<Function<String, String>, String, String> func3 = (ff, fs) -> "[" + ff.apply(fs) + "]";
		func3.apply(func2, "haha");
		func3.apply(fs -> fs.toUpperCase(), "haha");
	}

	@Getter
	@ToString
	public static class Person {
		private String name;
		private int age;

		public Person(String name, int age) {
			this.name = name;
			this.age = age;
		}
	}
}
