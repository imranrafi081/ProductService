package com.imr.productservice.commons;

import com.imr.productservice.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationCommons {
    public RestTemplate restTemplate;
    UserDto userDto;
    public AuthenticationCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto validateUser(String token){
       ResponseEntity<UserDto> userDtoResponseEntity = restTemplate.postForEntity("http://UserService/validate/"+token,null, UserDto.class);
       if(userDtoResponseEntity.getBody() == null)
            return null;
        return userDtoResponseEntity.getBody();
    }


}
