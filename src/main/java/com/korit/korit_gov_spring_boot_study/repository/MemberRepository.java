package com.korit.korit_gov_spring_boot_study.repository;

import com.korit.korit_gov_spring_boot_study.entity.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberRepository {
    private static MemberRepository instance;
    List<Member> memberList;

    private MemberRepository() {
        memberList = new ArrayList<>();
    }

    public static MemberRepository getInstance() {
        if (instance == null) {
            instance = new MemberRepository();
        }
        return instance;
    }

    public void addMember(Member member) {
        member.setMemberId(memberList.size() + 1);
        memberList.add(member);
    }

    public Optional<Member> findMemberByName(String name) {
        Member foundMember = memberList.stream()
                .filter(member -> member.getName().equals(name)).findFirst().orElse(null);
        if (foundMember == null) {
            return Optional.empty();
        }
        return Optional.of(foundMember);
    }

    public List<Member> getMemberList() {
        return memberList;
    }
}
