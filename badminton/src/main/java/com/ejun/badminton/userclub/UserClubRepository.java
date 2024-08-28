package com.ejun.badminton.userclub;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserClubRepository extends JpaRepository<UserClub, UserClub.UserClubId> {

}