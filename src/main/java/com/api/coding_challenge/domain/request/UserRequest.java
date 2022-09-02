package com.api.coding_challenge.domain.request;

import com.api.coding_challenge.config.ValidAge;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @JsonProperty("first_name")
    @NotBlank(message = "first name cannot be empty")
    @NotNull(message = "first name must be empty")
    @Size(max = 128, min = 1)
    private String firstName;

    @JsonProperty("last_name")
    @JsonSetter(value = "")
    @Size(max = 128, message = "last name must be greater 1 and not more than 128")
    private String lastName;

    @NotBlank(message = "email name cannot be empty")
    @NotNull(message = "email name must be empty")
    @JsonProperty("email")
    private String email;

    @JsonProperty("date_of_birth")
    @NotNull(message = "date of birth must be provided")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ValidAge(min=18)
    private LocalDate dateOfBirth;
}
