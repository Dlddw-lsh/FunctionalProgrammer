package org.example;


import org.example.entity.Author;
import org.example.entity.Tool;
import org.junit.Test;

import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class FunctionalInterfaceTest {

    private final List<Author> authors = Tool.getAuthors();

    @Test
    public void testFilterAnd01() {
        authors.stream()
                .filter(((Predicate<Author>) author -> author.getAge() > 17)
                        .and(author -> author.getName().length() > 1))
                .forEach(System.out::println);
    }

    @Test
    public void testFilterAnd02() {
        printNum((value -> value % 2 == 0), value -> value > 4);
    }

    @Test
    public void testFilterOr() {
        authors.stream()
                .filter(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getAge() > 17;
                    }
                }.or(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getName().length() < 2;
                    }
                }))
                .map(Author::getName)
                .forEach(System.out::println);
    }

    @Test
    public void testNegate() {
        authors.stream()
                .filter(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getAge() > 17;
                    }
                }.negate())
                .forEach(author -> System.out.println(author.getAge()));
    }

    public void printNum(IntPredicate predicate1, IntPredicate predicate2) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i : arr) {
            if (predicate1.and(predicate2).test(i)) {
                System.out.println(i);
            }
        }
    }
}
