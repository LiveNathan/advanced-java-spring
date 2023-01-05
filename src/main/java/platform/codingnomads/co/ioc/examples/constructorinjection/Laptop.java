package platform.codingnomads.co.ioc.examples.constructorinjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Laptop {

    private Processor processor;
    private OS os;
    private HardDrive hardDrive;
    private Monitor monitor;

    // these fields are being injected from the ApplicationContext
    // if a class contains only one constructor (as this one does), the use of @Autowired is optional
    // if a class contains two or more constructors, @Autowired is required for constructor injection to take place
    public Laptop(Processor processor, OS os, HardDrive hardDrive) {
        this.processor = processor;
        this.os = os;
        this.hardDrive = hardDrive;
    }
    // Autowired Setter
    @Autowired
    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public String printLaptopConfiguration() {
        return "processor: " + processor.getCore() + " core " + processor.getName() +
                "\nOS: " + os.getName();
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "processor=" + processor +
                ", os=" + os +
                ", hardDrive=" + hardDrive +
                ", monitor=" + monitor +
                '}';
    }
}
