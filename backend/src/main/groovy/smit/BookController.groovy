package smit


import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.validation.Valid
import smit.domain.Book

@CompileStatic
@ExecuteOn(TaskExecutors.BLOCKING)
@Controller('/book')
class BookController {
    private final BookRepository bookRepository

    BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository
    }

    @Get("/")
    List<Book> getAllBooks(@QueryValue String title) {
        if (title && !title.isEmpty()) {
            return bookRepository.findAll(title)
        } else {
            return bookRepository.findAll()
        }
    }

    @Get("/{id}")
    HttpResponse<Book> findById(@PathVariable Long id) {
        def book = bookRepository.findById(id)
        if (book.isPresent()) {
            return HttpResponse.ok(book.get())
        } else {
            return HttpResponse.notFound()
        }
    }

    @Post
    HttpResponse<Book> save(@Valid @Body Book book) {
        def savedBook = bookRepository.save(book)
        return HttpResponse.created(savedBook)
    }

    @Put("/{id}")
    HttpResponse<Book> updateBook(@PathVariable Long id, @Valid @Body Book book) {
        def updatedBook = bookRepository.updateBook(id, book)
        return HttpResponse.ok(updatedBook)
    }

    @Put("/{id}/availability")
    HttpResponse<Book> updateAvailability(@PathVariable Long id, boolean availability) {
        def updatedBook = bookRepository.updateAvailability(id, availability)
        return HttpResponse.ok(updatedBook)
    }

    @Delete("/{id}")
    HttpResponse deleteById(@PathVariable Long id) {
        bookRepository.deleteById(id)
        return HttpResponse.noContent()
    }
}
