package com.stockTrading.stockTradingSystem.model;

        import com.fasterxml.jackson.annotation.JsonIgnore;
        import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
        import lombok.AllArgsConstructor;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;

        import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(
        uniqueConstraints= @UniqueConstraint(columnNames={"username", "email"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDtl {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    @JsonIgnoreProperties(allowSetters = true)
    private String pwd;
    @Enumerated
    private UserRole userRole;
    private double cashBalance;
    private double usedCash;
    private long creationTime;

}
