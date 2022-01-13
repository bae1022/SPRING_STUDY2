package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

////DI 역할을 한다.
//public class AppConfig {
//
//    // 생성자 주입(injection)
//    public MemberService memberService(){
//        return new MemberServiceImpl(memberRepository());
//    }
//
//    public MemoryMemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
//
//    public OrderService orderService(){
//        return new OrderServiceImpl(memberRepository(), discountPolicy());
//    }
//
//    public DiscountPolicy discountPolicy(){
////        return new FixDiscountPolicy();
//        return new RateDiscountPolicy();
//    }
//}

// Spring 으로 변환 (@configuration -> 애플리케이션의 설정 정보라는 의미)
@Configuration // 얘 없으면 memberRepository가 3번 호출되게 된다.
public class AppConfig {

    // @bean 을 함으로써 스프링 컨테이너에 등록이 된다.

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()

    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() { // 한 번만 호출이 되었음
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
