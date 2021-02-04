package ir.webstack.blog.security;

import ir.webstack.blog.model.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String status;

    public User toUser(PasswordEncoder passwordEncoder){
        return new User(username, passwordEncoder.encode(password), firstName, lastName,status);
    }
}
