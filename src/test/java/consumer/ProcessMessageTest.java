package consumer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProcessMessageTest {

    private String xml = """
            <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
            <person>
                <name>testingnotebook</name>
                <role>admin</role>
            </person>
            """;

    @Test
    public void welcomeMessageTest() {
        String message = ProcessMessage.welcomeMessage(xml);
        assertEquals(message, "Welcome testingnotebook");
    }

}