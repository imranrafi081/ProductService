package com.imr.productservice.dtos;

import com.imr.productservice.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    public String name;
    public String email;
    public List<Role> roles;
}
