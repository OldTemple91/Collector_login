package swing.mobility.rider.collector.domain.model;


import lombok.*;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Table(name = "refresh_token")
@Entity
public class RefreshToken {

    @Id
    @Column(name = "Refresh_tokenKey")
    private String key;

    @Column
    private String value;

    public RefreshToken updateValue(String token) {
        this.value = token;
        return this;
    }

    @Builder
    public RefreshToken(String key, String value) {
        this.key = key;
        this.value = value;
    }


}
