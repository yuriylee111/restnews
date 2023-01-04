package com.lee.restnews.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {

    private Long id;

    @NotEmpty(message = "Name should not be empty")
    private String userName;

    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    @Size(min = 10, message = "Comment body must be minimum 10 characters")
    private String textBody;
}
