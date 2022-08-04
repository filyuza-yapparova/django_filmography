package entities;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public class Actor {
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String gender;

    public String getFullname() {
        return firstname + " " + lastname;
    }

    private static Faker faker = new Faker();

    public static Actor createFakeActor() {
        return Actor.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .birthdate(LocalDate.ofInstant(faker.date().birthday(12, 85).toInstant(), ZoneId.of("Europe/Moscow")))
                .gender("Male")
                .build();
    }

    public static Actor createTomCruise() {
        return Actor.builder()
                .firstname("Thomas")
                .lastname("Cruise")
                .birthdate(LocalDate.of(1962, 7, 3))
                .gender("Male")
                .build();
    }

    public static Actor createFakeInvalidActor() {
        return Actor.builder()
                .firstname(faker.name().firstName() + "*%*^%5")
                .lastname(faker.name().lastName() + "$^&*")
                .birthdate(LocalDate.ofInstant(faker.date().birthday(12, 85).toInstant(), ZoneId.of("Europe/Moscow")))
                .gender("Male")
                .build();
    }
}
