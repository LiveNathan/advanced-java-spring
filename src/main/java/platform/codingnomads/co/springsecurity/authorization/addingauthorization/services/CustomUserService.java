package platform.codingnomads.co.springsecurity.authorization.addingauthorization.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import platform.codingnomads.co.springsecurity.authorization.addingauthorization.models.UserMeta;
import platform.codingnomads.co.springsecurity.authorization.addingauthorization.models.UserPrincipal;
import platform.codingnomads.co.springsecurity.authorization.addingauthorization.repositories.UserMetaRepo;
import platform.codingnomads.co.springsecurity.authorization.addingauthorization.repositories.UserPrincipalRepo;

import java.util.List;

@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    UserPrincipalRepo userPrincipalRepo;

    @Autowired
    UserMetaRepo userMetaRepo;

    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        return userPrincipalRepo.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found with username or email : " + username)
        );
    }

    public UserMeta updateUserMeta(UserMeta userToUpdate) {
        UserMeta updatedUser = userMetaRepo.save(userToUpdate);
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userPrincipal.setUserMeta(userToUpdate);
        return updatedUser;
    }

    @PreAuthorize("#id != 7")  // This will cause the /current-user endpoint to fail us accessed by a user with id 7.
    public void printSomething(Long id) {
        System.out.println("User id: " + id);
    }

    @PostFilter(value = "filterObject.id != 11")  // Currently id 7 is nathan. It will be filtered out.
    public List<UserMeta> getAllUsers() {
        return userMetaRepo.findAll();
    }
}