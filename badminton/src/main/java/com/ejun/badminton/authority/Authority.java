package com.ejun.badminton.authority;

import com.ejun.badminton.enums.Method;
import com.ejun.badminton.enums.Target;
import com.ejun.badminton.memberLevel.MemberLevel;
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
public class Authority {
    @Id
    @GeneratedValue
    @Column(name = "authority_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Target target;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Method method;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private List<MemberLevel> memberLevels;

}
