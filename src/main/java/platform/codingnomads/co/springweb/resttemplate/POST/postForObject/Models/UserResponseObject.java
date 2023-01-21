package platform.codingnomads.co.springweb.resttemplate.POST.postForObject.Models;

import lombok.Data;

@Data
public class UserResponseObject {
    UserTemplate data;
    ErrorTemplate error;
    String status;
}