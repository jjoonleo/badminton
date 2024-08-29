package com.ejun.badminton.userclub;

import com.ejun.badminton.user.User;
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
public class UserClubController {

    private final UserClubService userClubService;

    @GetMapping("/{clubId}/members")
    public ResponseEntity<RestResponse<Iterable<GetClubMembersResponse>>> getClubMembers(@PathVariable Long clubId) {
        List<GetClubMembersResponse> clubMembers = userClubService.getClubMembers(clubId).stream().map(GetClubMembersResponse::from).toList();
        return ResponseEntity
                .status(SuccessCode.SUCCESS.getStatus())
                .body(RestResponse.fromSuccessCode(SuccessCode.SUCCESS, clubMembers));
    }

    @PostMapping("/join")
    public ResponseEntity<String> joinClub(@RequestBody JoinClubRequest joinClubRequest, @AuthenticationPrincipal User user) {
        userClubService.joinClub(joinClubRequest.getClubId(), joinClubRequest.getMemberLevelId(), user);
        return ResponseEntity.
                status(SuccessCode.JOINED.getStatus()).
                body(RestResponse.fromSuccessCode(SuccessCode.JOINED, null).getMessage());
    }
}
