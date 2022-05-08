package consumer;

import au.com.dius.pact.consumer.MessagePactBuilder;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.consumer.junit5.ProviderType;
import au.com.dius.pact.consumer.xml.PactXmlBuilder;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.annotations.PactDirectory;
import au.com.dius.pact.core.model.messaging.MessagePact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static au.com.dius.pact.consumer.dsl.Matchers.string;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "provider", providerType = ProviderType.ASYNCH)
@PactDirectory("pacts")
public class ProcessMessagePactTest {

    @Pact(consumer = "consumer")
    public MessagePact message(MessagePactBuilder builder) {
        return builder.expectsToReceive("process person XML to get welcome message")
                .withContent(new PactXmlBuilder("person").build(root -> {
                    root.appendElement("name", string("example name"));
                    root.appendElement("role", string("example role"));
                }))
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "message", pactVersion = PactSpecVersion.V3)
    public void shouldMatchSchema(MessagePact pact) {
        var welcomeMessage = ProcessMessage.welcomeMessage(
                pact.getMessages()
                        .stream()
                        .findFirst()
                        .get()
                        .contentsAsString()
        );
        assertEquals(welcomeMessage, "Welcome example name");
    }

}
