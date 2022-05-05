package com.example.hibernatestart;

import com.example.hibernatestart.dao.ChatRepository;
import com.example.hibernatestart.dao.CompanyRepository;
import com.example.hibernatestart.dao.UserChatRepository;
import com.example.hibernatestart.dao.UserRepository;
import com.example.hibernatestart.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.time.LocalDate;

@SpringBootApplication
@Slf4j
public class HibernateStartApplication {

    //private static final Logger log = LoggerFactory.getLogger(HibernateStartApplication.class);


    public static void main(String[] args) {
        try {
            var context = SpringApplication.run(HibernateStartApplication.class, args);
            var companyRepo = context.getBean(CompanyRepository.class);
            var userRepo = context.getBean(UserRepository.class);
            var userChatRepo = context.getBean(UserChatRepository.class);
            var chatRepo = context.getBean(ChatRepository.class);

            var gCompany = companyRepo.getCompanyByName("Google");
            gCompany.getUsers().forEach(System.out::println);


            User user1 = userRepo.getUserById(8L);

            Chat chat = Chat.builder()
                    .name("testChat")
                    .build();
            UserChat userChat = UserChat.builder()
                    .createdAt(Instant.now())
                    .createdBy(user1.getUsername())
                    .build();

            userChat.setUser(user1);
            userChat.setChat(chat);
            chatRepo.save(chat);
            userChatRepo.saveAndFlush(userChat);

//            *****
//            Company company0 = Company.builder()
//                    .name("TestCo")
//                    .build();
//
//            User user0 = User.builder()
//                    .username("leonid@mail.com")
//                    .build();
//            Profile profile = Profile.builder()
//                    .language("ru")
//                    .street("Street 15")
//                    .build();
//            company0.addUser(user0);
//            profile.setUser(user0);
//            companyRepo.saveAndFlush(company0);

//            ********
//            var cName = "Tesla";
//            Company company0 = Company.builder()
//                    .name(cName)
//                    .build();
//
//            User user0 = User.builder()
//                    .username("artem@mail.com")
//                    .build();
//            company0.addUser(user0);
//            companyRepo.saveAndFlush(company0);

            var companyName = "Amazon";
            Company company = companyRepo.getCompanyByName(companyName);
            if (company == null) {
                company = Company.builder()
                        .name(companyName)
                        .build();
                companyRepo.saveAndFlush(company);
                log.info("New Company {} saved", company.getName());
            }


            var userName = "egor@mail.com";
            User user = userRepo.getUserByUsername(userName);
            if (user == null) {
                user = User.builder()
                        .username(userName)
                        .personalInfo(PersonalInfo.builder()
                                .firstname("Egor")
                                .lastname("Egorov")
                                .birthDate(LocalDate.of(2000, 1, 10))
                                .build())
                        .role(Role.ADMIN)
                        .info("""
                                {
                                "name": "Egor",
                                "id": 24
                                }
                                """)
                        .company(company)
                        .build();
                log.trace("User entity created, object: {}", user);
                userRepo.saveAndFlush(user);
                log.info("User entity saved to database, object: {}", user);
            } else {
                log.info("User with username {} already exist", userName);
            }
        } catch (Exception ex) {
            log.error("Exception!!! {}, \nStacktrace: {}", ex.getMessage(), ex.getStackTrace());
        }

    }
}
