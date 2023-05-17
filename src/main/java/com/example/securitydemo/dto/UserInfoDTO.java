package com.example.securitydemo.dto;


import com.example.securitydemo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;


    public UserInfoDTO(User user){
        this.id = user.getId();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
    }
}
