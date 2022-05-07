package provider;

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SendMessageTest {

    @Test
    public void shouldBuildXML() throws JAXBException {
        Person person = new Person("testingnotebook", "admin");
        assertEquals(SendMessage.build(person), Fixtures.xml());
    }
}
