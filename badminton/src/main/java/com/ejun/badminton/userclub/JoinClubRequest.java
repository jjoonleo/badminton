package com.ejun.badminton.userclub;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JoinClubRequest {
    private Long clubId;
    private Long memberLevelId;
}
