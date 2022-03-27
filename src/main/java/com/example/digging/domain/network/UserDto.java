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


    private Integer userId;

    //고유식별자
    private String uid;


    @NotNull
    @Size(min = 3, max = 50)
    private String username;
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String password;

    private String provider;

    private String interest;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean activated;

    private LocalDateTime refreshTokenCreatedAt;

    private LocalDateTime refreshTokenUpdatedAt;


}
