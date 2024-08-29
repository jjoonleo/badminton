package com.ejun.badminton.club;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateClubRequest {

        private String name;
        private String description;
        private List<MemberLevel> memberLevels;

    public Club toEntity() {
        return Club.builder()
                .name(name)
                .description(description)
                .build();
    }

    @Data
    public static class MemberLevel {
        private String name;
        private List<Long> authorityIds;
    }
}
