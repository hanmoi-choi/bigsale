package service;

import com.bigsale.service.AddressService;
import com.bigsale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import template.AbstractSpringTest;

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

//    @Test
//    public void shallAddUserToDBProperly(){
//        User user = new User();
//        user.setUserId("hmchoi47");
//        user.setFirstName("Hanmoi");
//        user.setLastName("Choi");
//        user.setDateCreated(new Date());
//        user.setEmail("forhim185@gmail.com");
//        user.setUserLevel(User.UserLevel.BRONZE);
//        user.setUserType(User.UserType.ADMIN);
//        user.setPassword("Joan09ii");
//
//        Address address = new Address();
//        address.setCity("Melbourne");
//        address.setState("VIC");
//        address.setStreet("Prince St");
//        address.setZipcode("3168");
////        address.getUsers().add(buyer);
//
//        addressService.addAddress(address);
//
//
//        user.setAddress(address);
//        userService.deleteUser(user);
//        userService.addUser(user);
//
//        assertThat(userService.getAllUsers().size()).isPositive();
//    }

}
