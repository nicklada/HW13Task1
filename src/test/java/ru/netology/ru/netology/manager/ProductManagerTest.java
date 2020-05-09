package ru.netology.ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;
import ru.netology.repository.ProductRepository;
import ru.netology.manager.ProductManager;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    Book first = new Book(1,"Story1",1000,"Lada");
    TShirt second = new TShirt(2,"Story2",2000,"Mari");

    @BeforeEach
    void setup() {
        manager = new ProductManager(repository);
        manager.productAdd(first);
        manager.productAdd(second);

    }

    @Test
    void shouldFindIfExists() {
        int idToFind = 2;
        manager.findById(idToFind);
        Product expected = second;
        Product actual = manager.findById(idToFind);
        assertEquals(expected, actual);

    }

    @Test
    void shouldReturnNullIfNotExists() {
        int idToFind = 5;
        repository.findById(idToFind);
        Product expected = null;
        Product actual = manager.findById(idToFind);
        assertEquals(expected, actual);
    }

    @Test
    void shouldRemoveIfExists() {
        int idToRemove =1;
        manager.removeById(idToRemove);
        Product[] actual = manager.findAll();
        Product[] expected = new Product[]{second};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotRemoveIfNotExists() {
        int idToRemove = 4;
        assertThrows(RuntimeException.class, () -> manager.removeById(idToRemove));
    }

}