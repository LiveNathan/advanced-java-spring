package platform.codingnomads.co.ioc.examples.dependencylookup;

public class OtherGreeting implements GreetingProvider{
    @Override
    public String getGreeting() {
        return "Bom dia!";
    }
}
