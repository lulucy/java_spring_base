package com.example.doubleDataSource.test;

import java.util.*;
import java.util.function.Predicate;

public class TestJava8 {
    public static void main(String[] args) {
        Formula formula = new Formula() {
            @Override
            public double caluate(int a) {
                return (a*100);
            }
        };
        System.out.println(formula.caluate(3));
        System.out.println(formula.sqrt(4));

        List<String> names = Arrays.asList("zhangsan","lisi","wangwu");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        names.forEach(s -> {
            System.out.println("s = " + s);
        });

        Predicate<String> predicate = (s) -> s.length() > 0;
        System.out.println(predicate.test("foo"));
        System.out.println(predicate.negate().test("foo"));
        }
}
