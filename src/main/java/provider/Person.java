package provider;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
        name = "person"
)
public class Person {

    @XmlElement(
            required = true
    )
    private String name;

    @XmlElement(
            required = true
    )
    private String role;

    public Person() {
    }
    public Person(String name, String role) {
        this.name = name;
        this.role = role;
    }

}
