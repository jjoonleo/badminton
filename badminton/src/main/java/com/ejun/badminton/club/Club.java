package com.ejun.badminton.club;

import com.ejun.badminton.userclub.UserClub;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Club {
    @Id
    @GeneratedValue
    @Column(name = "club_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserClub> userClubs;
}
