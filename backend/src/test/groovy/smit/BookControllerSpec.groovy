package smit


import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.serde.ObjectMapper
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import smit.domain.Book
import spock.lang.Specification

import static io.micronaut.http.HttpStatus.*

@MicronautTest
class BookControllerSpec extends Specification {

    private BlockingHttpClient blockingClient

    @Inject
    @Client('/')
    HttpClient client

    @Inject
    ObjectMapper objectMapper

    void setup() {
        blockingClient = client.toBlocking()
    }


    void testFindNonExistingBookReturns404() {
        when:
        blockingClient.exchange(HttpRequest.GET('/book/99'))

        then:
        HttpClientResponseException e = thrown()
        e.response
        NOT_FOUND == e.status
    }

    void "test creating a Book"() {
        given:
        def book = new Book("aawaaa", "Peeter Tamm", 2022, true, "Raamatul on mingisugune kirjeldus")
        def request = HttpRequest.POST("/book", book)

        when:
        HttpResponse<Book> response = client.toBlocking().exchange(request, Book)

        then:
        response.status.code >= 200 && response.status.code < 300

        and:
        Book createdBook = response.body()
        print(createdBook)
        createdBook.title == book.title
        print(book)
    }

    void "test deleting a Book"() {
        given:
        Book book = new Book("Title", "Author", 2022, true, "Description")

        when:
        HttpRequest<Book> createRequest = HttpRequest.POST("/book", book)
        HttpResponse<Book> createResponse = client.toBlocking().exchange(createRequest, Book)

        then:
        createResponse.status == CREATED
        Book createdBook = createResponse.body()

        when:
        HttpRequest<Void> deleteRequest = HttpRequest.DELETE("/book/${createdBook.id}")
        HttpResponse<?> deleteResponse = client.toBlocking().exchange(deleteRequest)

        then:
        deleteResponse.status == NO_CONTENT
    }

    void "test fetching multiple books"() {
        given:
        def request = HttpRequest.GET("/book")

        when:
        HttpResponse<String> response = client.toBlocking().exchange(request, String)

        then:
        response.status == OK

        and:
        response.body() != null
        response.body().length() > 0

        and:
        response.body().toList().size() > 0
    }

    void "test updating a Book"() {
        given:
        Book book = new Book("Title", "Author", 2022, true, "Description")

        when:
        HttpRequest<Book> createRequest = HttpRequest.POST("/book", book)
        HttpResponse<Book> createResponse = client.toBlocking().exchange(createRequest, Book)
        Book createdBook = createResponse.body()

        Book updatedBook = new Book( "Updated Title", "Updated Author", 2023, false, "Updated Description")
        HttpRequest<Book> updateRequest = HttpRequest.PUT("/book/${createdBook.id}", updatedBook)
        HttpResponse<Book> updateResponse = client.toBlocking().exchange(updateRequest, Book)

        then:
        updateResponse.status == OK
        Book updatedBookResponse = updateResponse.body()
        updatedBookResponse.title == updatedBook.title
        updatedBookResponse.author == updatedBook.author
        updatedBookResponse.published == updatedBook.published
        updatedBookResponse.available == updatedBook.available
        updatedBookResponse.description == updatedBook.description
    }
}
