# message-pact-xml-jvm

## Purpose of Repo
Supporting solution for the post on https://testingnotebook.com/testing-xml-schema-with-jvm-message-pact/ see there for more details.

## Running Tests

Amend the broker URL to your own broker.

Run the consumer tests `mvn -Dtest=ProcessMessagePactTest test`

Publish consumer tests to broker `mvn pact:publish`

Run the provider tests `mvn -Dpact.verifier.publishResults=true -Dtest=SendMessagePactTest test`