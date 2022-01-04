package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) { // psvm
//        AppConfig appConfig = new AppConfig();
//
//        //        MemberService memberService = new MemberServiceImpl();
//        // ->
//        MemberService memberService = appConfig.memberService();
//
//        Member member = new Member(1L, "memberA", Grade.VIP); // command + option + v
//        memberService.join(member);
//
//        Member findMember = memberService.findMember(1L);
//        System.out.println("new member = " + member.getName()); //sout
//        System.out.println("find Member = " + findMember.getName());

        //spring 으로 변환
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);// 스프링 컨테이너 (객체들을 모두 관리)
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);// 기본적으로 메서드 이름으로 등록이 됨

        Member member = new Member(1L, "memberA", Grade.VIP); // command + option + v
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName()); //sout
        System.out.println("find Member = " + findMember.getName());


    }
}
