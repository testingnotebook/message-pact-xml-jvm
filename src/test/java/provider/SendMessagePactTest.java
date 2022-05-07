package provider;

import au.com.dius.pact.provider.MessageAndMetadata;
import au.com.dius.pact.provider.PactVerifyProvider;
import au.com.dius.pact.provider.junit5.MessageTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Consumer;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;

import static com.google.common.collect.ImmutableList.of;

@Provider("provider")
@Consumer("consumer")
@PactBroker(url="http://localhost:9393")
public class SendMessagePactTest {

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void testTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @BeforeEach
    void before(PactVerificationContext context) {
        context.setTarget(new MessageTestTarget(of(this.getClass().getPackage().getName())));
    }

    @PactVerifyProvider("process person XML to get welcome message")
    public MessageAndMetadata testInsertActivityId() throws JAXBException {
        var person = new Person("testingnotebook", "admin");
        return new MessageAndMetadata(SendMessage.build(person).getBytes(), new HashMap<>());
    }
}

