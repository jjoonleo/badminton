package com.ejun.badminton.club;

import com.ejun.badminton.user.User;
import com.ejun.badminton.userclub.UserClubService;
import com.ejun.badminton.utils.RestResponse;
import com.ejun.badminton.utils.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clubs")
@RequiredArgsConstructor
public class ClubController {

    private final UserClubService userClubService;
    private final ClubRepository clubRepository;
    private final ClubService clubService;

    @PostMapping("/{clubId}/join")
    public ResponseEntity<String> joinClub(@PathVariable Long clubId, @AuthenticationPrincipal User user) {
        userClubService.joinClub(clubId, user);
        return ResponseEntity.
                status(SuccessCode.JOINED.getStatus()).
                body(RestResponse.fromSuccessCode(SuccessCode.JOINED, null).getMessage());
    }

    @PostMapping()
    public ResponseEntity<RestResponse<CreateClubResponse>> createClub(@RequestBody Club club, @AuthenticationPrincipal User user) {
        CreateClubResponse createClubResponse = CreateClubResponse.from(clubService.createClub(club, user));
        return ResponseEntity
                .status(SuccessCode.CLUB_CREATED.getStatus())
                .body(RestResponse.fromSuccessCode(SuccessCode.CLUB_CREATED,createClubResponse));
    }

    @GetMapping()
    public ResponseEntity<RestResponse<Iterable<GetClubResponse>>> getClubs(@AuthenticationPrincipal User user) {
        List<GetClubResponse> clubs = clubRepository.findClubsByUserId(user.getId()).stream().map(GetClubResponse::fromClubWithoutUserClub).toList();
        return ResponseEntity
                .status(SuccessCode.CLUB_LOADED.getStatus())
                .body(RestResponse.fromSuccessCode(SuccessCode.CLUB_LOADED, clubs)
        );
    }

    @GetMapping("/{clubId}")
    public ResponseEntity<RestResponse<GetClubResponse>> getClub(@PathVariable Long clubId) {
        GetClubResponse club = GetClubResponse.fromClub(clubService.getClubById(clubId));
        return ResponseEntity.status(SuccessCode.CLUB_LOADED.getStatus())
                .body(RestResponse.fromSuccessCode(SuccessCode.CLUB_LOADED, club));
    }
}
