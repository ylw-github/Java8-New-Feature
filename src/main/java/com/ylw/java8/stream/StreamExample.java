package com.ylw.java8.stream;

import com.ylw.java8.stream.entity.Person;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @desc: Stream流特性
 * @author: YangLinWei
 * @createTime: 2020/12/2 11:47 上午
 */
public class StreamExample {

    private static List<Person> personList = new ArrayList<>();

    static {
        personList.add(new Person("张三", 18, 1));
        personList.add(new Person("李四", 20, 2));
        personList.add(new Person("王五", 16, 2));
        personList.add(new Person("赵六", 32, 1));
        personList.add(new Person("陆七", 19, 1));
    }

    public static void main(String[] args) {
        //testStream();
        //testParallelStream();
        //testFilter();
        //testMap();
        //testSorted();
        //testLimit();
        //testMapTo();
        //testCollect();
        //testDistinct();
        testSkip();;
    }

    /**
     * 测试串行流
     */
    private static void testStream() {
        long startTime = System.currentTimeMillis();
        personList.stream().forEach(person -> {
            System.out.println(person.toString());
        });
        System.out.println("stream consume time -> " + (System.currentTimeMillis() - startTime));
    }

    /**
     * 测试并行流
     */
    private static void testParallelStream() {
        long startTime = System.currentTimeMillis();
        personList.parallelStream().forEach(person -> {
            System.out.println(person.toString());
        });
        System.out.println("parallelStream consume time -> " + (System.currentTimeMillis() - startTime));
    }

    private static void testFilter() {
        personList.stream().filter(person -> person.getSex() == 1).forEach(person -> {
            System.out.println("男生 ->" + person.toString());
        });
    }

    private static void testMap() {
        List<String> nameList = personList.stream().map(Person::getName).collect(Collectors.toList());
        nameList.forEach(name -> {
            System.out.println(name);
        });
    }

    private static void testSorted() {
        System.out.println("------ 按年龄升序排序 ------");
        personList.stream().sorted(Comparator.comparing(Person::getAge)).forEach(person -> {
            System.out.println(person.toString());
        });
        System.out.println("------ 按年龄降序排序 ------");
        personList.stream().sorted(Comparator.comparing(Person::getAge).reversed()).forEach(person -> {
            System.out.println(person.toString());
        });
    }

    private static void testLimit() {
        personList.stream().limit(2).forEach(person -> {
            System.out.println(person.toString());
        });
    }

    private static void testMapTo() {
        IntSummaryStatistics intSummaryStatistics = personList.stream().mapToInt(Person::getAge).summaryStatistics();
        System.out.println("总条目数 ——> " + intSummaryStatistics.getCount());
        System.out.println("总年龄 ——>" + intSummaryStatistics.getSum());
        System.out.println("最大年龄 ——>" + intSummaryStatistics.getMax());
        System.out.println("最小年龄 ——>" + intSummaryStatistics.getMin());
        System.out.println("平均年龄 ——>" + intSummaryStatistics.getAverage());
    }

    private static void testCollect() {
        List<String> nameList = personList.stream().map(Person::getName).collect(Collectors.toList());
        String names = nameList.stream().collect(Collectors.joining(","));
        System.out.println("names ->" + names);
    }

    private static void testDistinct() {
        int[] ints = {1, 1, 2, 2, 3, 3, 3, 4, 4, 4, 4};
        Arrays.stream(ints).distinct().forEach(number -> {
            System.out.println("number ->" + number);
        });
    }

    private static  void testSkip(){
        personList.stream().skip(4).forEach(person -> {
            System.out.println(person.toString());
        });
    }


}

