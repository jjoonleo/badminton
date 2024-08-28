package com.ejun.badminton.club;

import com.ejun.badminton.user.User;
import com.ejun.badminton.userclub.UserClubService;
import com.ejun.badminton.utils.Exceptions.ClubNotFoundException;
import com.ejun.badminton.utils.Exceptions.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;
    private final UserClubService userClubService;

    public Club createClub(Club club, User user) {
        Club createdClub = clubRepository.save(club);
        userClubService.joinClub(createdClub.getId(), user);
        return createdClub;
    }

    public Club getClubById(Long id) throws CustomException {
        Optional<Club> optionalClub = clubRepository.findById(id);
        if(optionalClub.isEmpty()) {
            throw new ClubNotFoundException();
        }
        return optionalClub.orElse(null);
    }
}
