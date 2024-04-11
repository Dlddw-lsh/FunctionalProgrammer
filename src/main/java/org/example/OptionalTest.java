package org.example;

import org.example.entity.Author;
import org.junit.Test;

import java.util.Optional;

/**
 * Optional的用法
 */
public class OptionalTest {

    public Optional<Author> getAuthorOptional() {
        Author author = new Author(1L, "aaa", 33, "bbb", null);
        return Optional.of(author);
    }

    @Test
    public void testOptional() {
        Optional<Author> authorOptional = getAuthorOptional();

        Author author = authorOptional.orElseGet(() -> new Author(1L, "ccc", 33, "bbb", null));
        System.out.println(author.getName());
    }

    @Test
    public void testOptionalFilter() {
        Optional<Author> authorOptional = getAuthorOptional();
        Optional<Author> author1 = authorOptional.filter(author -> author.getAge() > 44);
        author1.ifPresent(System.out::println);
    }

    @Test
    public void testOptionalMap() {
        Optional<Author> authorOptional = getAuthorOptional();
        authorOptional.map(Author::getBooks)
                .ifPresent(books -> books.forEach(System.out::println));
    }
}