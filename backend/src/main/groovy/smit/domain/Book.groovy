package smit.domain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.CompileStatic
import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@CompileStatic
@Serdeable
@Entity
@Introspected
class Book {

    @Id
    @JsonProperty('id')
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    Long id

    @NotNull
    @Size(min = 5, max = 100)
    String title

    @NotNull
    @Size(min = 5, max = 100)
    String author

    @NotNull
    @Min(-4000)
    @Max(2024)
    int published

    boolean available

    @Size(max = 1000)
    String description

    Book() {}

    @JsonCreator
    Book(@NotNull String title,
         @NotNull String author,
         int published,
         boolean available,
         String description
    ) {
        this.title = title
        this.author = author
        this.published = published
        this.available = available
        this.description = description
    }
}