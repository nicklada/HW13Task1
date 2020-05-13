package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;
import ru.netology.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    ProductRepository repository = new ProductRepository();
    Book first = new Book(1, "Story1", 1000, "Lada");
    TShirt second = new TShirt(2, "Story2", 2000, "Mari");

    @Test
    void shouldSave() {
        repository.save(first);
        Product[] expected = new Product[]{first};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturnEmpty() {
        Product[] expected = new Product[]{};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturn() {
        repository.save(first);
        repository.save(second);
        Product[] expected = new Product[]{first, second};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindIfExists() {
        repository.save(first);
        repository.save(second);
        int idToFind = 2;
        repository.findById(idToFind);
        Product expected = second;
        Product actual = repository.findById(idToFind);
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnNullIfNotExists() {
        repository.save(first);
        repository.save(second);
        int idToFind = 5;
        repository.findById(idToFind);
        Product expected = null;
        Product actual = repository.findById(idToFind);
        assertEquals(expected, actual);
    }

    @Test
    void shouldRemoveIfExists() {
        int idToRemove =1;
        repository.save(first);
        repository.save(second);
        repository.removeById(idToRemove);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{second};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotRemoveIfNotExists() {
        int idToRemove = 4;
        repository.save(first);
        repository.save(second);
        assertThrows(NotFoundException.class, () -> repository.removeById(idToRemove));
    }
}