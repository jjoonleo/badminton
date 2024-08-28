package com.ejun.badminton.userclub;

import com.ejun.badminton.club.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserClubRepository extends JpaRepository<UserClub, UserClub.UserClubId> {
    List<UserClub> findByClubIn(Collection<Club> clubs);
}