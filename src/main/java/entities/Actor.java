package entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public class Actor {
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String gender;
}
