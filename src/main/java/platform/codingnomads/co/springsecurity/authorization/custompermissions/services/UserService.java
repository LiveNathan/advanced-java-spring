package platform.codingnomads.co.springsecurity.authorization.custompermissions.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.codingnomads.co.springsecurity.authorization.custompermissions.models.User;
import platform.codingnomads.co.springsecurity.authorization.custompermissions.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User reverseEmail(String email) {
        User user = getUser(email);
        StringBuilder newEmail = new StringBuilder();
        newEmail.append(user.getEmail());
        newEmail.reverse();
        user.setEmail(newEmail.toString());
        User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
