package platform.codingnomads.co.springweb.resttemplate.GET.getForObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import platform.codingnomads.co.springweb.resttemplate.GET.models.ExcuseTemplate;
import platform.codingnomads.co.springweb.resttemplate.GET.models.QuoteTemplate;

import java.util.Arrays;

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

            // submit more requests here
            
//            CodingNomadsTasksApiResponse response =
//                    restTemplate.getForObject("http://demo.codingnomads.co:8080/tasks_api/users/5",
//                            CodingNomadsTasksApiResponse.class);
//
//            System.out.println(response.toString());

        };
    }
}
