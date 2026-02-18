package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {
        @Bean()
        // destroyMethod는 지정해주지 않아도 close 혹은 shutdown이라는 이름의 메서드가 있으면 자동으로 호출해준다.
        // 추론이 싫으면 destroyMethod = ""으로 지정해주면 된다.
        // 코드를 고칠 수 없는 라이브러리를 초기화, 종료해야하면 @Bean의 initMethod, destroyMethod를 사용하자.
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
