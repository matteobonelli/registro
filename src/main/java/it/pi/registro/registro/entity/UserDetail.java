package it.pi.registro.registro.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_detail")
@Getter
@Setter
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String address;
    @Column
    private String city;

//    @OneToOne(mappedBy = "userDetail")
//    private User user;
}
