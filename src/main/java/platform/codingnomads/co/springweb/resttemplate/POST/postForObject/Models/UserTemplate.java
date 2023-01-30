package platform.codingnomads.co.springweb.resttemplate.POST.postForObject.Models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserTemplate {
    private long id;
    private String email;
    private String first_name;
    private String last_name;
    private long createdAt;
    private long updatedAt;
}
