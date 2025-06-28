package mini.integration.customerservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mini.integration.customerservice.domain.enumtype.MemberShipType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Set;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberShipPolicy {

    public static final int NEWCOMER_YEAR = 0;
    public static final int REGULAR_YEAR = 2;
    public static final int PATRON_YEAR = 5;
    public static final int LEGACY_YEAR = 8;

    public static final BigDecimal NEWCOMER_BALANCE = BigDecimal.valueOf(0);
    public static final BigDecimal REGULAR_BALANCE = BigDecimal.valueOf(10000);
    public static final BigDecimal PATRON_BALANCE = BigDecimal.valueOf(20000);
    public static final BigDecimal LEGACY_BALANCE = BigDecimal.valueOf(50000);

    private MemberShipType memberShipType;
    private BigDecimal requiredPostedBalanced;
    private int requiredLoyaltyYear;

    public static Set<MemberShipPolicy> POLICIES = Set.of(
        MemberShipPolicy.builder()
            .memberShipType(MemberShipType.BRONZE)
            .requiredPostedBalanced(NEWCOMER_BALANCE)
            .requiredLoyaltyYear(NEWCOMER_YEAR)
            .build(),

        MemberShipPolicy.builder()
            .memberShipType(MemberShipType.SILVER)
            .requiredPostedBalanced(REGULAR_BALANCE)
            .requiredLoyaltyYear(REGULAR_YEAR)
            .build(),

        MemberShipPolicy.builder()
            .memberShipType(MemberShipType.GOLD)
            .requiredPostedBalanced(PATRON_BALANCE)
            .requiredLoyaltyYear(PATRON_YEAR)
            .build(),

        MemberShipPolicy.builder()
            .memberShipType(MemberShipType.DIAMOND)
            .requiredPostedBalanced(LEGACY_BALANCE)
            .requiredLoyaltyYear(LEGACY_YEAR)
            .build()
    );

    public static MemberShipType getMemberShip(BigDecimal postedBalance, OffsetDateTime registeredDate) {
        return MemberShipPolicy.POLICIES.stream().filter(policy -> {
                long loyaltyDuration = ChronoUnit.YEARS.between(OffsetDateTime.now(), registeredDate);
                return policy.getRequiredPostedBalanced().compareTo(postedBalance) >= 0 ||
                    loyaltyDuration <= policy.getRequiredLoyaltyYear();
            }).max(Comparator.comparing(MemberShipPolicy::getMemberShipType))
            .map(MemberShipPolicy::getMemberShipType)
            .orElse(MemberShipType.BRONZE);

    }
}