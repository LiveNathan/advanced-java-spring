package platform.codingnomads.co.springsecurity.authorization.custompermissions.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import platform.codingnomads.co.springsecurity.authorization.custompermissions.models.User;
import platform.codingnomads.co.springsecurity.authorization.custompermissions.services.UserService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String home() {
        return "permissions";
    }

    @GetMapping("/user")
    @ResponseBody
    @PostAuthorize("hasPermission(returnObject, 'READ')")
    public User getEntityById(@RequestParam String email) {
        return userService.getUser(email);
    }

    @GetMapping("/user/delete/{id}")
    @ResponseBody
    @PreAuthorize("hasPermission(#id, 'platform.codingnomads.co.springsecurity.authorization.custompermissions.models.User', 'DELETE')")
    public String deleteEntity(@PathVariable Long id) {
        userService.deleteUser(id);
        return ("deleted user with id: " + id);
    }

    // Create at least two additional controller methods, utilizing your CustomPermissionEvaluator to establish permission for each.
    @GetMapping("/user-all")
    @ResponseBody
    @PostAuthorize("hasPermission(returnObject, 'READ')") // This endpoint returns Access Denied, which may be correct, but I don't understand it.
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user-reverse")
    @ResponseBody
    @PostAuthorize("hasPermission(returnObject, 'READ')")
    public User reverseEmail(@RequestParam String email) {
        return userService.reverseEmail(email);
    }
}
