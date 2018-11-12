package com.borovyksv.model.auction;

import com.borovyksv.model.Constants;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.borovyksv.model.auction.Address.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
@ToString
@EqualsAndHashCode

@Entity
public class User {
    private static final String BILLING_PREFIX = "billing_";
    private static final String SHIPPING_PREFIX = "shipping_";

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    private String firstName;
    private String lastName;
    private String userName;
    private Address homeAddress;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime lastModification;

    @AttributeOverride(name = STREET, column = @Column(name = BILLING_PREFIX + STREET))
    @AttributeOverride(name = ZIPCODE, column = @Column(name = BILLING_PREFIX + ZIPCODE))
    @AttributeOverride(name = CITY, column = @Column(name = BILLING_PREFIX + CITY))
    private Address billingAddress;

    @AttributeOverride(name = STREET, column = @Column(name = SHIPPING_PREFIX + STREET))
    @AttributeOverride(name = ZIPCODE, column = @Column(name = SHIPPING_PREFIX + ZIPCODE))
    @AttributeOverride(name = CITY, column = @Column(name = SHIPPING_PREFIX + CITY))
    private Address shippingAddress;
}
