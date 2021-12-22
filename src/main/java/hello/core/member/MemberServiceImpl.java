package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository = new MemoryMemberRepository(); // command + shift + enter; 자동완성

    @Override
    public void join(Member member) {
        memberRepository.save(member); // 다형성에 의해서 MemoryMemberRepository의 save가 호출
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
