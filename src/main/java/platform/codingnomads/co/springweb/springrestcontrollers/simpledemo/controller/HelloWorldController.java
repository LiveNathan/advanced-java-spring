package platform.codingnomads.co.springweb.springrestcontrollers.simpledemo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import platform.codingnomads.co.springweb.resttemplate.PUT.models.Task;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class HelloWorldController {

    @RequestMapping(path = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String hello() {
        return "Hello Spring Developer!";
    }

    @RequestMapping(path = "/hello/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String greeting(@PathVariable(name = "name") String name) {
        return "Hello " + name + "!";
    }

    @RequestMapping(path = "/hello/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List helloList() {
        List<Integer> integerList = List.of(1, 2, 3, 4);
        return integerList;
    }

    @RequestMapping(path = "/hello/pojo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Task helloTask() {
        Task task = Task.builder().userId(479).name("Build bars.").description("So many bars.").completed(false).build();
        return task;
    }
}




