package platform.codingnomads.co.springweb.resttemplate.POST.postForObject.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseObject {
    UserTemplate data;
    ErrorTemplate error;
    String status;
}