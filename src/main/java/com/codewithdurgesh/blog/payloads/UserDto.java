package com.codewithdurgesh.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private int id;
	@NotEmpty
	@Size(min = 4, message = "Username must be min of 4 characters")
	private String name;
	@Email(message = "Email addressis not valid !!")
	private String email;
	@NotEmpty
	private String password;
	@NotEmpty
	@Size(min = 3, max = 10, message = "Password must be min of 3 chars and max of 10 chars !!")
	private String about;
}
