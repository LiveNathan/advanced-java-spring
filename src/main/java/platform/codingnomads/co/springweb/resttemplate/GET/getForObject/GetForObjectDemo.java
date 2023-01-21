package platform.codingnomads.co.springweb.resttemplate.GET.getForObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import platform.codingnomads.co.springweb.resttemplate.GET.models.BoredTemplate;
import platform.codingnomads.co.springweb.resttemplate.GET.models.ExcuseTemplate;
import platform.codingnomads.co.springweb.resttemplate.GET.models.QuoteTemplate;

import java.util.Arrays;
import java.util.HashMap;

@SpringBootApplication
public class GetForObjectDemo {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(GetForObjectDemo.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            // Quotes
            QuoteTemplate[] randomQuote;
            randomQuote = restTemplate.getForObject("https://zenquotes.io/api/random/", QuoteTemplate[].class);
            System.out.println(Arrays.toString(randomQuote));

            // Excuses
            ExcuseTemplate[] randomExcuse;
            randomExcuse = restTemplate.getForObject("https://excuser-three.vercel.app/v1/excuse/party/2", ExcuseTemplate[].class);
            System.out.println(Arrays.toString(randomExcuse));

            // Bored
            BoredTemplate randomActivity;
            randomActivity = restTemplate.getForObject("http://www.boredapi.com/api/activity?participants=1", BoredTemplate.class);
            System.out.println(randomActivity);

            // Pass parameters with Map
            HashMap<String, Integer> boredApiParameters = new HashMap<String, Integer>();
            boredApiParameters.put("participants", 2);
//            randomActivity = restTemplate.getForObject("http://www.boredapi.com/api/activity?participants=2", BoredTemplate.class, boredApiParameters);
//            System.out.println(randomActivity);
            ResponseEntity<BoredTemplate> responseEntity = restTemplate.getForEntity("http://www.boredapi.com/api/activity?participants=2", BoredTemplate.class, boredApiParameters);
            if (responseEntity.getStatusCode().equals(HttpStatus.OK) && responseEntity.getBody() != null) {
                BoredTemplate boredTemplate = responseEntity.getBody();
                System.out.println(boredTemplate);
            } else {
                System.out.println("Something went wrong!");
            }
            // submit more requests here

//            CodingNomadsTasksApiResponse response =
//                    restTemplate.getForObject("http://demo.codingnomads.co:8080/tasks_api/users/5",
//                            CodingNomadsTasksApiResponse.class);
//
//            System.out.println(response.toString());

        };
    }
}
