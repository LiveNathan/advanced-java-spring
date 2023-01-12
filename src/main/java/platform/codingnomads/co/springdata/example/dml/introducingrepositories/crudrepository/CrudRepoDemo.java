package platform.codingnomads.co.springdata.example.dml.introducingrepositories.crudrepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CrudRepoDemo implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CrudRepoDemo.class);
    }

    //autowire the UserRepo into the class to gain access to the CRUD methods
    @Autowired
    UserRepo userRepo;

    @Override
    public void run(String... args) throws Exception {
        //create new user
        User user = User.builder().firstName("Bobby").lastName("Bobbert").age(56).build();
        User user2 = User.builder().firstName("Joanne").lastName("Joanna").age(36).build();
        User user1 = User.builder().firstName("Nathan").lastName("Lively").age(42).build();
        User user3 = User.builder().firstName("Elis").lastName("Bradshaw").age(41).build();

        //save user and assign what is returned to the user variable.
//        user = userRepo.save(user);
//        user2 = userRepo.save(user2);
        List<User> userList = (List<User>) userRepo.saveAll(List.of(user, user1, user2, user3));

        Iterable<User> users = userRepo.findAll();

        for(User u : users){
            System.out.println(u.toString());
        }

        //delete the user using the id of the inserted user object
        userRepo.deleteById(user.getId());
        userRepo.deleteById(user2.getId());
    }
}
