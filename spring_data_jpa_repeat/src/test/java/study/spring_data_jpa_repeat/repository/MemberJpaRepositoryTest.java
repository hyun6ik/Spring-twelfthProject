package study.spring_data_jpa_repeat.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import study.spring_data_jpa_repeat.entitiy.Member;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class MemberJpaRepositoryTest {

    @Autowired MemberJpaRepository memberJpaRepository;

    @Test
    public void memberTest() throws Exception {
        //given
        Member member = new Member("member1");
        //when
        Member savedMember = memberJpaRepository.save(member);
        Member findMember = memberJpaRepository.find(savedMember.getId());
        //then
        assertThat(findMember).isEqualTo(savedMember);
        assertThat(findMember.getId()).isEqualTo(savedMember.getId());

    }

    @Test
    public void basicCRUD() throws Exception {
        //given
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);
        //when
        // 단건 조회 검증
        Member findMember1 = memberJpaRepository.findById(member1.getId()).orElse(null);
        Member findMember2 = memberJpaRepository.findById(member2.getId()).orElse(null);

        // 리스트 조회 검증
        List<Member> result = memberJpaRepository.findAll();

        // 카운트 검증
        long count = memberJpaRepository.count();

        // 삭제 검증
        memberJpaRepository.delete(member1);
        memberJpaRepository.delete(member2);
        long deletedCount = memberJpaRepository.count();
        //then
        assertThat(member1.getId()).isEqualTo(findMember1.getId());
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        assertThat(result.size()).isEqualTo(2);

        assertThat(count).isEqualTo(2);
        assertThat(deletedCount).isEqualTo(0);
    }

    @Test
    public void findByUsernameAndAgeGreaterThen() throws Exception {
        //given
        Member m1 = new Member("AAA", 10);
        Member m2= new Member("AAA", 20);
        memberJpaRepository.save(m1);
        memberJpaRepository.save(m2);
        //when
        List<Member> result = memberJpaRepository.findByUsernameAndAgeGreaterThen("AAA", 15);
        //then
        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.size()).isEqualTo(1);
    }


}