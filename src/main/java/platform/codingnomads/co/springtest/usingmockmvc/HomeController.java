package platform.codingnomads.co.springtest.usingmockmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("/")
@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "Bobbert");
        return "greeting";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String greet() {
        return "Hello Back";
    }

    // create at least three new controller methods
    @GetMapping("/goodbye")
    @ResponseBody
    public String goodbye() {
        return "GOODBYE";
    }

    @GetMapping("/redirect")
    @ResponseBody
    public void method(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", "/index");
        httpServletResponse.setStatus(308);
    }

    @GetMapping("/empty")
    @ResponseBody
    public String empty() {
        return "";
    }

}
