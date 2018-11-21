package com.borovyksv.model.associations.onetoone.foreigngenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Address {

    @Id
    @GeneratedValue(    generator = "addressKeyGenerator")
    @GenericGenerator(  name = "addressKeyGenerator",
                        strategy = "foreign",
                        parameters = @Parameter(
                                name = "property", value = "user"
                        ))
    private Long id;
    protected String street;
    protected String zipcode;
    protected String city;

    @OneToOne(optional = false)         // created foreign key constraint
    @PrimaryKeyJoinColumn               // Address must have a reference to a User
    protected User user;

    public Address(String street, String zipcode, String city, User user) {
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
        this.user = user;
    }
}
