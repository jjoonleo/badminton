package com.ejun.badminton.userclub;

import com.ejun.badminton.club.Club;
import com.ejun.badminton.memberLevel.MemberLevel;
import com.ejun.badminton.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"user", "club"})
public class UserClub {

    @EmbeddedId
    private UserClubId id;

    @Column(nullable = false)
    private java.sql.Timestamp dateOfJoin;

    @Column(nullable = true)
    private String dateOfWithdrawal;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("clubId")
    @JoinColumn(name = "club_id")
    private Club club;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_level_id", nullable = false)
    private MemberLevel memberLevel;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserClubId implements Serializable {
        private Long userId;
        private Long clubId;

        // Getters and setters, equals() and hashCode()
    }
}
