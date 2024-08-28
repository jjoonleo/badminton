package com.ejun.badminton.club;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Club> findByName(String name);

    interface ClubWithoutUserClub {
        Long getId();
        String getName();
        String getDescription();
    }

    @Query(value = "SELECT c.club_id, c.name, c.description From user_club uc LEFT JOIN club c ON uc.club_id = c.club_id WHERE uc.user_id = :userId", nativeQuery = true)
    List<ClubWithoutUserClub> findClubsByUserId(@Param("userId") Long userId);
}
