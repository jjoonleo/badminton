package com.ejun.badminton.club;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateClubResponse {

    private Long id;
    private String name;
    private String description;

    public static CreateClubResponse from(Club club) {
        return CreateClubResponse.builder()
                .id(club.getId())
                .name(club.getName())
                .description(club.getDescription())
                .build();
    }
}
