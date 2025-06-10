package mini.integration.customerservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mini.integration.customerservice.domain.enumtype.Gender;

import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "customer_seq", insertable = false, updatable = false)
    private Integer seq;

    @NotNull(message = "First Name is required")
    @Size(max = 100, message = "First Name length must be 1 to 100")
    private String firstName;

    @NotNull(message = "Last Name is required")
    @Size(max = 100, message = "Last Name length must be 1 to 100")
    private String lastName;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @Embedded
    private Contact contact;

    @Embedded
    private Address address;



//    private  Set<FileInfo> fileInfos;
}
