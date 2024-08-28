package com.ejun.badminton.club;

import com.ejun.badminton.user.User;
import com.ejun.badminton.userclub.UserClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/clubs")
@RequiredArgsConstructor
public class ClubController {

    private final UserClubService userClubService;
    private final ClubRepository clubRepository;
    private final ClubService clubService;

    @PostMapping("/{clubId}/join")
    public ResponseEntity<String> joinClub(@PathVariable Long clubId, @AuthenticationPrincipal User user) {
        userClubService.joinClub( clubId, user);
        return ResponseEntity.ok("User joined the club successfully");
    }

    @PostMapping()
    public ResponseEntity<CreateClubResponse> createClub(@RequestBody Club club, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(CreateClubResponse.from(clubService.createClub(club, user)));
    }

    @GetMapping()
    public ResponseEntity<Iterable<ClubRepository.ClubWithoutUserClub>> getClubs(@AuthenticationPrincipal User user) {
        Logger.getLogger("User").info(user.getId().toString());
        return ResponseEntity.ok(clubRepository.findClubsByUserId(user.getId()));
    }

    @GetMapping("/{clubId}")
    public ResponseEntity<Club> getClub(@PathVariable Long clubId) {
        return ResponseEntity.ok(clubService.getClubById(clubId));
    }

    @GetMapping("/{clubId}/members")
    public ResponseEntity<Set<User>> getClubMembers(@PathVariable Long clubId) {
        try {
            return ResponseEntity.ok(new HashSet<>(userClubService.getClubMembers(clubId)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
