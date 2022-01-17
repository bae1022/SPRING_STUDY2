package hello.core;


import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//        basePackages = "hello.core.member",
//        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
) // 컴포넌트 스캔을 사용하면 @Configuration 붙은 설정 정보도 자동으로 등록된다. 때문에 앞서 만들어두었던 설정 정보들이 함께 등록되기 때문에, 컴포넌트 설정 정보를 제외한다. (실무에서는 x)
public class AutoAppConfig {

    @Bean(name = "memoryMemberRepository") // 이름이 같은 경우, 수동 빈 등록이 우선권을 가진다. (수동 빈이 자동 빈을 오버라이딩 한다.) -> 오류는 나지 않음.
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

}
