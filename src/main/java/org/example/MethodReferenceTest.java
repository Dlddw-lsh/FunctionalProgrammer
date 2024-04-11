package org.example;

import org.example.entity.Author;
import org.example.entity.Tool;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


public class MethodReferenceTest {
    private final List<Author> authors = Tool.getAuthors();

    @Test
    public void test01() {
        StringBuilder stringBuilder = new StringBuilder();
        authors.stream().map(Author::getName).forEach(stringBuilder::append);
        System.out.println(stringBuilder);
    }

    @Test
    public void test02() {
        System.out.println(subAuthorName("lsh", String::substring));
    }

    @Test

    public void test03() {
        authors.stream().map(Author::getName).map(StringBuilder::new).map(stringBuilder -> stringBuilder.append("-lsh")).forEach(System.out::println);
    }

    @Test
    public void test04() {
        authors.stream().map(Author::getAge).map(age -> age + 10).filter(age -> age > 18).map(age -> age + 2).forEach(System.out::println);
        authors.stream().mapToInt(Author::getAge).map(age -> age + 10).filter(age -> age > 18).map(age -> age + 2).forEach(System.out::println);
    }


    @Test
    public void test05() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Optional<Integer> sum = stream.parallel()// 转换为并行流
                .peek(item -> System.out.println(item + "-->" + Thread.currentThread().getName()))
                .filter(num -> num > 5).reduce(Integer::sum);
        sum.ifPresent(System.out::println);

    }

    public String subAuthorName(String str, UseString useString) {
        int start = 0;
        int length = 1;
        return useString.use(str, start, length);
    }
}

interface UseString {
    String use(String str, int start, int end);
}