package jpabook.start;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.LearnJPA.LearnJpaApplication;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = LearnJpaApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
public class MemberTest {
	@Value("${spring.datasource.url}")
    private String URL;
    @Value("${spring.datasource.driver-class-name}")
    private String DRIVERNAME;
    @Value("${spring.datasource.username}")
    private String USERNAME;
    @Value("${spring.datasource.password}")
    private String PASSWORD;
 
    @Test
    void connTest() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVERNAME);
        try(Connection conn = 
                DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            System.out.println("connTest: "+conn);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
// 다른 패키지에서 다른 소스 테스트 하는 방법 찾는중    
//    @Autowired
//    MemberRepository memberRepository;
//    
//    @PostConstruct
//    public void init() {
//        memberRepository.save(new Member("Man1", 12));
//    }
//    
//    @Test
//    void queryTest() throws SQLException, ClassNotFoundException {
//        Optional<Member> result=memberRepository.findById(1L);
//        System.out.println("queryTest: "+result);
//    }
}
