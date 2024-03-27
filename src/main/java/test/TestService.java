package test;

import model.User;
import orm.UserService;

public class TestService {

    public static void main(String[] args) {

        UserService userService = new UserService();
        User user = new User();
        user.setName("Ayman");
        user.setMail("ayman@User.com");
        user.setMdp("123");
        userService.addUser(user);


    }

}
