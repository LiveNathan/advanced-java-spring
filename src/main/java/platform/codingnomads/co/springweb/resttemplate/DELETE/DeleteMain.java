package platform.codingnomads.co.springweb.resttemplate.DELETE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import platform.codingnomads.co.springweb.resttemplate.POST.postForObject.Models.UserResponseObject;
import platform.codingnomads.co.springweb.resttemplate.POST.postForObject.Models.UserTemplate;

@SpringBootApplication
public class DeleteMain {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DeleteMain.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            /* DONE
            Task newTask = Task.builder()
                    .name("should be deleted")
                    .description("used in a delete RestTemplate example. If you see this something went wrong. Oops")
                    //be sure to enter a valid user id
                    .userId(380)
                    .completed(false)
                    .build();

            //POST new task to server
            ResponseObject responseObject = restTemplate
                    .postForObject("http://demo.codingnomads.co:8080/tasks_api/tasks/", newTask, ResponseObject.class);

            //confirm data was returned & avoid NullPointerExceptions
            if (responseObject == null) {
                throw new Exception("The server did not return anything. Not even a ResponseObject!");
            } else if (responseObject.getData() == null) {
                throw new Exception("The server encountered this error while creating the task:" + responseObject.getError().getMessage());
            } else {
                newTask = responseObject.getData();
            }

            System.out.println("The task was successfully created");
            System.out.println(newTask);

            //delete the newTask using the ID the server returned
            restTemplate.delete("http://demo.codingnomads.co:8080/tasks_api/tasks/" + newTask.getId());
            System.out.println("The task was also successfully deleted");

            //try to GET, verify record was deleted
            try {
                restTemplate.getForEntity(
                        "http://demo.codingnomads.co:8080/tasks_api/tasks/" + newTask.getId(), ResponseObject.class);
            } catch (HttpClientErrorException e) {
                System.out.println(e.getMessage());
            }

            //delete using exchange()
            HttpEntity<Task> httpEntity = new HttpEntity<>(newTask);
            try {
                restTemplate.exchange("http://demo.codingnomads.co:8080/tasks_api/tasks/"
                        + newTask.getId(), HttpMethod.DELETE, httpEntity, ResponseObject.class);
            } catch (HttpClientErrorException e) {
                System.out.println(e.getMessage());
            } */
            UserTemplate userTemplate = UserTemplate.builder().email("bill@bob.com").first_name("Bill").last_name("Bob").build();

            // POST new User to server
            UserResponseObject responseObject = restTemplate.postForObject("http://demo.codingnomads.co:8080/tasks_api/users/", userTemplate, UserResponseObject.class);

            // Confirm data was returned & avoid NullPointerExceptions
            if (responseObject == null) {
                throw new Exception("The server did not return anything. Not even a ResponseObject!");
            } else if (responseObject.getData() == null) {
                throw new Exception("The server encountered this error while creating the user:" + responseObject.getError().getMessage());
            } else {
                userTemplate = responseObject.getData();
            }

            System.out.println("The user was successfully created");
            System.out.println(userTemplate);

            // Delete the User using the ID the server returned
            restTemplate.delete("http://demo.codingnomads.co:8080/tasks_api/users/" + userTemplate.getId());
            System.out.println("The user was also successfully deleted");

            // Try to GET, verify record was deleted
            try {
                restTemplate.getForEntity(
                        "http://demo.codingnomads.co:8080/tasks_api/users/" + userTemplate.getId(), UserResponseObject.class);
            } catch (HttpClientErrorException e) {
                System.out.println(e.getMessage());
            }

            // Delete using exchange()
            HttpEntity<UserTemplate> httpEntity = new HttpEntity<>(userTemplate);
            try {
                restTemplate.exchange("http://demo.codingnomads.co:8080/tasks_api/users/"
                        + userTemplate.getId(), HttpMethod.DELETE, httpEntity, UserResponseObject.class);
            } catch (HttpClientErrorException e) {
                System.out.println(e.getMessage());
            }
        };
    }
}
