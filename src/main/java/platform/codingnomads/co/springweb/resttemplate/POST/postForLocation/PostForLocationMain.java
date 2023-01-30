package platform.codingnomads.co.springweb.resttemplate.POST.postForLocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import platform.codingnomads.co.springweb.resttemplate.POST.postForObject.Models.UserResponseObject;
import platform.codingnomads.co.springweb.resttemplate.POST.postForObject.Models.UserTemplate;

@SpringBootApplication
public class PostForLocationMain {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(PostForLocationMain.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            /* Done
            Task newTask = Task.builder()
                    .name("learn how to use postForLocation()")
                    .description("get comfortable using the RestTemplate postForLocation() method")
                    //be sure to use a valid user id
                    .userId(380)
                    .completed(false)
                    .build();

            //use postForLocation() to get the URL for the new resource
            URI returnedLocation = restTemplate
                    .postForLocation("http://demo.codingnomads.co:8080/tasks_api/tasks", newTask, ResponseObject.class);

            System.out.println(Objects.requireNonNull(returnedLocation));

            ResponseEntity<?> responseEntity = restTemplate
                    .postForEntity("http://demo.codingnomads.co:8080/tasks_api/tasks", newTask, ResponseObject.class);

            System.out.println(responseEntity.getHeaders().get("Location")); */

            // New User
            UserTemplate newUser = UserTemplate.builder().email("salt2@shaker.com").first_name("Salt2").last_name("Shaker").build();
//            URI returnedLocation = restTemplate.postForLocation("http://demo.codingnomads.co:8080/tasks_api/users", newUser, UserResponseObject.class);
//            System.out.println(Objects.requireNonNull(returnedLocation));

            ResponseEntity<?> responseEntity = restTemplate.postForEntity("http://demo.codingnomads.co:8080/tasks_api/users", newUser, UserResponseObject.class);
            System.out.println(responseEntity.getHeaders().get("Location"));


        };
    }
}
