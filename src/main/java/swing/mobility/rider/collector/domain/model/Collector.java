package swing.mobility.rider.collector.domain.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"id", "name"})
@Entity
@Getter
@Table(name = "collector")
public class Collector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collector_id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_no", nullable = false)
    private String phoneNumber;

    @Column(name = "band_id", nullable = false)
    private Long bandId;

    @Enumerated(EnumType.STRING)
    private Authority authority;


}
