package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
////    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
////    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
//
//    //dip 위반 사항 수정 -> dip 위반만 수정하면 코드가 돌아가지 못한다.
//    private DiscountPolicy discountPolicy; // final은 값이 할당되어야 하므로 지운다.

    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    @Autowired(required = false) // 수정자 주입(setter 주입)/ 주입할 대상이 없어도 동작하게 하려면 required = false
    public void setMemberRepository(MemberRepository memberRepository){
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    @Autowired // 수정자 주입(setter 주입)
    public void setDiscountPolicy(DiscountPolicy discountPolicy){
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }

    @Autowired // 생성자가 하나이기 때문에 @Autowired 를 생략해도 자동 주입이 될 수 있다.
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override //단일 책임 원칙이 잘 지켜졌음 (할인에 대한 변경사항이 들어와도 건드릴 필요가 없다.)
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice); //최종 생성 주문 반환
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
