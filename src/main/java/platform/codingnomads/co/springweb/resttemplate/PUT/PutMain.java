package platform.codingnomads.co.springweb.resttemplate.PUT;

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

@SpringBootApplication
public class PutMain {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(PutMain.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            /* DONE
            //use a valid task id
            int taskId = 171;

            //request Task 5 from server
            ResponseObject responseObject = restTemplate
                    .getForObject("http://demo.codingnomads.co:8080/tasks_api/tasks/" + taskId, ResponseObject.class);

            //confirm data was retrieved & avoid NullPointerExceptions
            Task taskToUpdate;
            if (responseObject == null) {
                throw new Exception("The server did not return anything. Not even a ResponseObject!");
            } else if (responseObject.getData() == null) {
                throw new Exception("The task with ID " + taskId + " could not be found");
            } else {
                taskToUpdate = responseObject.getData();
            }

            //update the task information
            taskToUpdate.setName("updated using put() - video demo ");
            taskToUpdate.setDescription("this task was updated using RestTemplate's put() method - video demo");
            taskToUpdate.setCompleted(false);

            //use put to update the resource on the server
            restTemplate.put("http://demo.codingnomads.co:8080/tasks_api/tasks/" + taskToUpdate.getId(), taskToUpdate);
            //get the task to verify update
            responseObject = restTemplate.getForObject(
                    "http://demo.codingnomads.co:8080/tasks_api/tasks/" + taskId, ResponseObject.class);
            System.out.println(responseObject.toString());

            taskToUpdate.setName("updated using exchange() PUT - video demo 2");
            taskToUpdate.setDescription("this task was updated using RestTemplate's exchange() method - video demo 2");

            //create an HttpEntity wrapping the task to update
            HttpEntity<Task> httpEntity = new HttpEntity<>(taskToUpdate);
            //use exchange() to PUT the HttpEntity, and map the response to a ResponseEntity
            ResponseEntity<ResponseObject> response = restTemplate.exchange(
                    "http://demo.codingnomads.co:8080/tasks_api/tasks/" + taskToUpdate.getId(),
                    HttpMethod.PUT, httpEntity, ResponseObject.class);
            System.out.println(response.toString()); */
            // Use a valid User id
            int userId = 479;

            // Request user from server
            UserResponseObject responseObject = restTemplate.getForObject("http://demo.codingnomads.co:8080/tasks_api/users/" + userId, UserResponseObject.class);

            // Confirm data was retrieved & avoid NullPointerExceptions
            UserTemplate userToUpdate;
            if (responseObject == null) {
                throw new Exception("The server did not return anything. Not even a ResponseObject!");
            } else if (responseObject.getData() == null) {
                throw new Exception("The task with ID " + userId + " could not be found");
            } else {
                userToUpdate = responseObject.getData();
            }

            // Update the user information
            userToUpdate.setEmail("Updated@email.address");
            userToUpdate.setFirst_name("Updated");
            userToUpdate.setLast_name("Last");

            // Use PUT to update the resource on the server
            restTemplate.put("http://demo.codingnomads.co:8080/tasks_api/users/" + userToUpdate.getId(), userToUpdate);

            // GET the task to verify update
            responseObject = restTemplate.getForObject("http://demo.codingnomads.co:8080/tasks_api/users/" + userId, UserResponseObject.class);
            System.out.println(responseObject.toString());

            userToUpdate.setFirst_name("updated using exchange() PUT");
            userToUpdate.setLast_name("lastName updated using RestTemplate's exchange() method");

            // Create an HttpEntity wrapping the task to update
            HttpEntity<UserTemplate> httpEntity = new HttpEntity<>(userToUpdate);

            // Use exchange() to PUT the HttpEntity, and map the response to a ResponseEntity
            ResponseEntity<UserResponseObject> response = restTemplate.exchange(
                    "http://demo.codingnomads.co:8080/tasks_api/users/" + userToUpdate.getId(),
                    HttpMethod.PUT, httpEntity, UserResponseObject.class);
            System.out.println(response.toString());
        };
    }
}
