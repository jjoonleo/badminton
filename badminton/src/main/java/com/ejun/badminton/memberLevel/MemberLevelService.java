package com.ejun.badminton.memberLevel;

import com.ejun.badminton.authority.Authority;
import com.ejun.badminton.authority.AuthorityRepository;
import com.ejun.badminton.club.Club;
import com.ejun.badminton.club.CreateClubRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberLevelService {
    private final MemberLevelRepository memberLevelRepository;
    private final AuthorityRepository authorityRepository;

    public MemberLevel createMemberLevel(MemberLevel memberLevel) {
        return memberLevelRepository.save(memberLevel);
    }

    public void createMemberLevelFromClubMemberLevel(CreateClubRequest.MemberLevel memberLevel, Club club) {
        List<Authority> authorities = authorityRepository.findAllById(memberLevel.getAuthorityIds());
        if(authorities.isEmpty()) {
            throw new IllegalArgumentException("Authorities does not exist");
        }
        MemberLevel newMemberLevel = MemberLevel.builder()
                .name(memberLevel.getName())
                .club(club)
                .authorities(authorities)
                .build();
        memberLevelRepository.save(newMemberLevel);
    }
}
