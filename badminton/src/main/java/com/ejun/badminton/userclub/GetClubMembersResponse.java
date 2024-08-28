package com.ejun.badminton.userclub;

import com.ejun.badminton.user.BadmintonSkill;
import com.ejun.badminton.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetClubMembersResponse {
    private Long userId;
    private String username;
    private String email;
    private java.sql.Timestamp dateOfJoin;
    private String dateOfBirth;
    private String dateOfWithdrawal;
    private BadmintonSkill badmintonSkill;

    public static GetClubMembersResponse from(User user) {
        return GetClubMembersResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .dateOfJoin(user.getDateOfJoin())
                .dateOfBirth(user.getDateOfBirth())
                .dateOfWithdrawal(user.getDateOfWithdrawal())
                .badmintonSkill(user.getBadmintonSkill())
                .build();
    }

}
