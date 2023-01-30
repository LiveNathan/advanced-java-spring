package platform.codingnomads.co.springweb.resttemplate.PATCH;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import platform.codingnomads.co.springweb.resttemplate.POST.postForObject.Models.UserResponseObject;
import platform.codingnomads.co.springweb.resttemplate.POST.postForObject.Models.UserTemplate;

import java.util.Objects;

@SpringBootApplication
public class PatchMain {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(PatchMain.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            /* DONE
            //create an empty Task
            Task task = new Task();

            //be sure to use a valid task id
            task.setId(234);

            //set fields you want to change. All other fields are null and will not be updated
            task.setName("use patchForObject()");
            task.setDescription("this task was updated using patchForObject()");

            //send the PATCH request using the URL, the Task created above and the ResponseObject Class
            ResponseObject objectResponse = restTemplate
                    .patchForObject("http://demo.codingnomads.co:8080/tasks_api/tasks/" + task.getId(), task, ResponseObject.class);

            System.out.println(Objects.requireNonNull(objectResponse));

            task.setName("PATCH using exchange()");
            task.setDescription("This task was updated using PATCH");

            HttpEntity<Task> httpEntity = new HttpEntity<>(task);
            ResponseEntity<ResponseObject> response = restTemplate
                    .exchange("http://demo.codingnomads.co:8080/tasks_api/tasks/" + task.getId(), HttpMethod.PATCH, httpEntity, ResponseObject.class);

            System.out.println(Objects.requireNonNull(response)); */
            // Create an empty User
            UserTemplate userTemplate = UserTemplate.builder().build();

            // Use a valid User id
            userTemplate.setId(479);

            // Set fields. All other fields are null and will not be updated.
            userTemplate.setFirst_name("use patchForObject()");
            userTemplate.setLast_name("this lastName was updated using patchForObject()");

            // Send the PATCH request using the URL, the User created above and the UserResponseObject Class
            UserResponseObject objectResponse = restTemplate.patchForObject("http://demo.codingnomads.co:8080/tasks_api/users/" + userTemplate.getId(), userTemplate, UserResponseObject.class);

            System.out.println(Objects.requireNonNull(objectResponse));

            userTemplate.setFirst_name("PATCH using exchange()");
            userTemplate.setLast_name("This user was updated using PATCH");

            HttpEntity<UserTemplate> httpEntity = new HttpEntity<>(userTemplate);
            ResponseEntity<UserResponseObject> response = restTemplate.exchange("http://demo.codingnomads.co:8080/tasks_api/users/" + userTemplate.getId(), HttpMethod.PATCH, httpEntity, UserResponseObject.class);

            System.out.println(Objects.requireNonNull(response));
        };
    }
}
