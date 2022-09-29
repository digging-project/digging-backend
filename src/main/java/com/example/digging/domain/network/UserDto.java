package com.example.digging.domain.network;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull
    @Size(min = 3, max = 6)
    private String username;
    private String email;


    public static class Interest {
        public Integer interestId;
        public String interestTitle;
    }

    public Interest interest;

}
