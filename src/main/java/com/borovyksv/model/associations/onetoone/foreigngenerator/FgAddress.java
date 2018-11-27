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
public class FgAddress {

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
    @PrimaryKeyJoinColumn               // FgAddress must have a reference to a FgUser
    protected FgUser user;

    public FgAddress(String street, String zipcode, String city, FgUser user) {
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
        this.user = user;
    }
}
