package platform.codingnomads.co.ioc.lab.initial;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
@RequiredArgsConstructor
public class CodingNomad {

    private final JDK jdk;
    private final IDE ide;
    private final Framework framework;
    private final Computer computer;
    private Desk desk;
    private final Mouse mouse;

    public String createAwesomeSoftware() {
        return MessageFormat
                .format("This coding nomad is creating awesome software using, " +
                                "IDE: ({0}:{1}), JDK: ({2}:{3}), Framework: ({4}:{5}), Computer: ({6}:{7}), Desk: ({8}:{9})",
                        ide.getName(),
                        ide.getVersion(),
                        jdk.getName(),
                        jdk.getVersion(),
                        framework.getName(),
                        framework.getVersion(),
                        computer.getBrand(),
                        computer.getModel(),
                        desk.getBrand(),
                        desk.getModel(),
                        mouse.getBrand(),
                        mouse.getModel()
                );
    }
    // Setter injection
    @Autowired
    public void setDesk(Desk desk) {
        this.desk = desk;
    }
}