package consumer;

import jakarta.xml.bind.JAXB;

import java.io.StringReader;

public class ProcessMessage {

    public static String welcomeMessage(String message) {
        Person person = JAXB.unmarshal(new StringReader(message), Person.class);
        return "Welcome %s".formatted(person.getName());
    }
}
