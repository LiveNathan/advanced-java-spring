package platform.codingnomads.co.springweb.resttemplate.POST.postForObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import platform.codingnomads.co.springweb.resttemplate.POST.postForObject.Models.UserResponseObject;
import platform.codingnomads.co.springweb.resttemplate.POST.postForObject.Models.UserTemplate;

@SpringBootApplication
public class PostForObjectMain {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(PostForObjectMain.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            /* already ran this once
            Task newTask = Task.builder()
                    .name("learn how to use postForObject() - video demo")
                    .description("get comfortable using the RestTemplate postForObject() method")
                    //use a valid user id
                    .userId(380)
                    .completed(false)
                    .build();

            ResponseObject taskReturnedByServerAfterPost = restTemplate
                    .postForObject("http://demo.codingnomads.co:8080/tasks_api/tasks", newTask, ResponseObject.class);

            if (taskReturnedByServerAfterPost != null) {
                System.out.println(taskReturnedByServerAfterPost.toString());
            }  */

            // New user
            UserTemplate newUser = UserTemplate.builder().email("pepper2@build.com").first_name("Pepper2").last_name("Build").build();
            UserResponseObject userReturnedByServerAfterPost = restTemplate.postForObject("http://demo.codingnomads.co:8080/tasks_api/users", newUser, UserResponseObject.class);
            if (userReturnedByServerAfterPost != null){
                System.out.println(userReturnedByServerAfterPost.toString());
            } else {
                System.out.println("Returned null.");
            }
        };
    }
}
