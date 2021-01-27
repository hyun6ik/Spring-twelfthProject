package study.spring_data_jpa_repeat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.spring_data_jpa_repeat.entitiy.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findTop3By();
}
