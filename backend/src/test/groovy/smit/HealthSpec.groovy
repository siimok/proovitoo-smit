package smit

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest
class HealthSpec extends Specification {

    @Inject
    @Client("/")
    HttpClient client

    void "health endpoint exposed" () {
        when:
        HttpStatus status = client.toBlocking().retrieve(HttpRequest.GET("/health"), HttpStatus)

        then:
        status == HttpStatus.OK
    }
}
