package hello.core.member;

public class MemberServiceImpl implements MemberService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository(); // command + shift + enter; 자동완성

    // 추상화에 의존
    private final MemberRepository memberRepository;

    // 생성자를 통해 memberRepository 에 어떤 구현체가 들어갈지 설정
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member); // 다형성에 의해서 MemoryMemberRepository의 save가 호출
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
