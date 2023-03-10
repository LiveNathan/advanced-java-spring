package platform.codingnomads.co.springtest.usingtestresttemplate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import platform.codingnomads.co.springtest.usingtestresttemplate.models.CoffeePreference;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = UsingTestRestTemplateMain.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoffeePreferenceControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void testPostCoffeePreference() throws Exception {

        //build new CoffeePreference to post
        CoffeePreference preferenceToPost = CoffeePreference.builder()
                .type("Black")
                .size(CoffeePreference.Size.LARGE)
                .sugar(false)
                .iced(true)
                .intensity(9)
                .build();

        //send POST request using TestRestTemplate
        ResponseEntity<CoffeePreference> postedCoffeePreference =
                testRestTemplate.postForEntity("/coffee", preferenceToPost, CoffeePreference.class);

        //confirm Location header is correct
        String locationHeader = Objects.requireNonNull(postedCoffeePreference.getHeaders().getLocation()).toString();
        assertThat(locationHeader).isEqualTo("http://www.url.com/new/location");

        //confirm ID was assigned
        assertThat(Objects.requireNonNull(postedCoffeePreference.getBody()).getId()).isNotNull();
    }

    @Test
    public void testGetCoffeePreference() throws Exception {
        // Send GET request using TestRestTemplate
        ResponseEntity<CoffeePreference> coffeePreferenceResponseEntity =
                testRestTemplate.getForEntity("/coffee/1", CoffeePreference.class);

        // Confirm the ID of the returned object is correct
        assertThat(Objects.requireNonNull(coffeePreferenceResponseEntity.getBody()).getId()).isNotNull();

        // Delete the object using TestRestTemplate
        testRestTemplate.delete("/coffee/1");  // This is not working for some reason.
    }
}
