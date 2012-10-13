package service;

import com.bigsale.controller.dto.UserSearchDto;
import com.bigsale.orm.model.Level;
import com.bigsale.orm.model.User;
import com.bigsale.service.AddressService;
import com.bigsale.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import template.AbstractSpringTest;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 30/09/12
 * Time: 12:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserServiceImplTest extends AbstractSpringTest{

    @Autowired
    UserService userService;
    @Autowired
    AddressService addressService;

    @Test
    public void couldCheckUserIdDuplication(){
        User user = new User();
        user.setUserId("hmchoi46");

        assertThat(userService.checkIdDuplication(user.getUserId())).isTrue();
    }

    @Test
    @Transactional
    public void couldFindUserWithPartialUserId(){
        UserSearchDto searchDto = new UserSearchDto();
        searchDto.setUserId("hm");

        List<User> userList =  userService.findUserBySearchCriteria(searchDto);

        assertThat(userList.size()).isGreaterThan(0);
        assertThat(userList.get(0).getUserId()).isEqualTo("hmchoi46");
    }

    @Test
    public void couldUpdateUseNextLevelProperly(){
        User user = new User();
        user.setUserId("hm");
        user.setLoginCount(5);
        user.setUserLevel(Level.BRONZE);
        userService.updateUserInfoWithLogin(user);
        assertThat(user.getUserLevel()).isEqualTo(Level.SILVER);

    }
}
