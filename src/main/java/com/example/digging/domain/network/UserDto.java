package com.example.digging.domain.network;

import com.example.digging.domain.entity.Authority;
import com.example.digging.domain.entity.Tags;
import com.example.digging.domain.entity.UserHasPosts;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
