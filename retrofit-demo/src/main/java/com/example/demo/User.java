package com.example.demo;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String login;
    private long id;
    private String url;
}
