package provider;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

import java.io.StringWriter;

public class SendMessage {

    public static String build(Person person) throws JAXBException {
        final JAXBContext requestFormat = JAXBContext.newInstance(Person.class);
        final StringWriter sw = new StringWriter();
        requestFormat.createMarshaller().marshal(person, sw);
        return sw.toString();

    }
}
