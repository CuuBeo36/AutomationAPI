package api.fullFlow.pojo;

import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class User {
    public String email;
    public String password;


    public void generateLoginUser() {
        Faker faker = new Faker();
        this.setEmail(faker.internet().emailAddress());
        this.setPassword(faker.internet().password());
    }
}
