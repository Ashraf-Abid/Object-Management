package net.abid.journalApp.service;

import net.abid.journalApp.entity.User;
import net.abid.journalApp.repository.UserRepo;
import org.assertj.core.internal.Arrays;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class CustomUserServiceImplTest {
    @InjectMocks
    private CustomUserServiceImpl userService;
    @MockBean
    private  UserRepo userRepo;
    //here when we testing with mockito. we are not going to use orginal findbyUser of repo
    // because it is a costly operation.
    //so when it is called from test, ans is set by use...because ourmain intensiton
    //is to test loadUserByusername..
    void loadUserTest(){
        when(userRepo.findByusername(ArgumentMatchers.anyString())).thenReturn(User.builder().roles(new ArrayList<>()).username("abid").password("asdasdasd").build());
        UserDetails user = userService.loadUserByUsername("abid");
        Assertions.assertNotNull(user);
    }
}
