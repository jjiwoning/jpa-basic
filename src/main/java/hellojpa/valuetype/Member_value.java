package hellojpa.valuetype;

import javax.persistence.*;

@Entity
public class Member_value {

    @Id
    @GeneratedValue
    @Column(name = "member_value_id")
    private Long id;

    private String username;

    //period
    @Embedded
    private Period workPeriod;

    //address
    @Embedded
    private Address homeAddress;
}
