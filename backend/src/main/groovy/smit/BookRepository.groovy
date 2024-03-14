package smit

import book.domain.Book
import io.micronaut.transaction.annotation.ReadOnly
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceException
import jakarta.persistence.TypedQuery

@Singleton
class BookRepository {

    private final EntityManager entityManager

    BookRepository(EntityManager entityManager) {
        this.entityManager = entityManager
    }

    @ReadOnly
    Optional<Book> findById(long id) {
        Optional.ofNullable(entityManager.find(Book, id))
    }

    @Transactional
    Book save(Book book) {
        entityManager.persist(book)
        book
    }

    @Transactional
    void deleteById(long id) {
        findById(id).ifPresent(entityManager::remove)
    }

    @ReadOnly
    List<Book> findAll() {
        String queryStr = "SELECT b FROM Book b"
        TypedQuery<Book> query = entityManager.createQuery(queryStr, Book)
        query.resultList
    }


    @Transactional
    Book updateBook(Long id, Book updatedBook) {
        def book = entityManager.find(Book, id)
        if (book) {
            book.title = updatedBook.title ?: book.title
            book.author = updatedBook.author ?: book.author
            book.published = updatedBook.published ?: book.published
            book.available = updatedBook.available
            book.description = updatedBook.description ?: book.description
            return entityManager.merge(book)
        } else {
            throw new PersistenceException("Book with id $id not found")
        }
    }

    @Transactional
    Book updateAvailability(Long id, boolean availability) {
        def book = entityManager.find(Book, id)
        if (book) {
            book.available = availability
            entityManager.merge(book)
            return book
        } else {
            throw new PersistenceException("Book with id $id not found")
        }
    }
}