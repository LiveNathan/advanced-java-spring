package platform.codingnomads.co.corespring.examples.autowiredannotation;

import org.springframework.beans.factory.annotation.Autowired;

public class LaptopComputer {
    @Autowired
    private VideoCard videoCard;
    @Autowired
    private EVO evo;
    @Autowired
    private RME rme;
}
