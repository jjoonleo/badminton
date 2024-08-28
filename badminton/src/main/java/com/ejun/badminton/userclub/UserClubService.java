package com.ejun.badminton.userclub;

import com.ejun.badminton.club.Club;
import com.ejun.badminton.club.ClubRepository;
import com.ejun.badminton.user.User;
import com.ejun.badminton.user.UserRepository;
import com.ejun.badminton.utils.Exceptions.ClubNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserClubService {
    private final ClubRepository clubRepository;
    private final UserClubRepository userClubRepository;

    public void joinClub(Long clubId, User user){
        Club club = clubRepository.findById(clubId).orElseThrow(ClubNotFoundException::new);
        UserClub userClub = UserClub.builder()
                .user(user)
                .club(club)
                .dateOfJoin(new java.sql.Timestamp(System.currentTimeMillis()))
                .id(new UserClub.UserClubId(user.getId(), club.getId()))
                .build();
        userClubRepository.save(userClub);
    }

    public List<User> getClubMembers(Long clubId) {
        return clubRepository.findById(clubId)
                .map(club -> club.getUserClubs().stream()
                        .map(UserClub::getUser)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new IllegalArgumentException("Club not found"));
    }
}
