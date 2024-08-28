package com.ejun.badminton.club;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetClubResponse {
    private Long clubId;
    private String clubName;
    private String clubDescription;

    public static GetClubResponse fromClub(Club club) {
        return GetClubResponse.builder()
                .clubId(club.getId())
                .clubName(club.getName())
                .clubDescription(club.getDescription())
                .build();
    }

    public static GetClubResponse fromClubWithoutUserClub(ClubRepository.ClubWithoutUserClub club) {
        return GetClubResponse.builder()
                .clubId(club.getId())
                .clubName(club.getName())
                .clubDescription(club.getDescription())
                .build();
    }
}
