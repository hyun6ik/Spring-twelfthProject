package study.spring_data_jpa_repeat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.spring_data_jpa_repeat.entitiy.Member;

public interface MemberRepository extends JpaRepository<Member,Long> {

}
