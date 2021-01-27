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

@SpringBootTest
@Transactional
@Commit
public class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    public void memberTest() throws Exception {
        //given
        Member member = new Member("memberA");
        //when
        Member savedMember = memberRepository.save(member);
        Member findMember = memberRepository.findById(savedMember.getId()).get();
        //then
        assertThat(savedMember.getId()).isEqualTo(findMember.getId());
        assertThat(savedMember.getUsername()).isEqualTo(findMember.getUsername());
        assertThat(savedMember).isEqualTo(findMember);

    }

    @Test
    public void basicCRUD() throws Exception {
        //given
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        // 단건 조회 검증
        Member findMember1 = memberRepository.findById(member1.getId()).orElse(null);
        Member findMember2 = memberRepository.findById(member2.getId()).orElse(null);

        // 리스트 조회 검증
        List<Member> result = memberRepository.findAll();

        // 카운트 검증
        long count = memberRepository.count();

        // 삭제 검증
        memberRepository.delete(member1);
        memberRepository.delete(member2);
        long deletedCount = memberRepository.count();
        //then
        assertThat(member1.getId()).isEqualTo(findMember1.getId());
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        assertThat(result.size()).isEqualTo(2);

        assertThat(count).isEqualTo(2);
        assertThat(deletedCount).isEqualTo(0);


    }
}
