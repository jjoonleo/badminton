package com.ejun.badminton.memberLevel;

import com.ejun.badminton.authority.Authority;
import com.ejun.badminton.club.Club;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberLevel {
    @Id
    @GeneratedValue
    @Column(name = "member_level_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    @ManyToMany
    @JoinTable(
            name = "member_level_authority",
            joinColumns = @JoinColumn(name = "member_level_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private List<Authority> authorities;

}
