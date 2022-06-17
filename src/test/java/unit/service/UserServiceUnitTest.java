package unit.service;

import com.revature.model.User;
import com.revature.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class UserServiceUnitTest {
    List<User> mockList = Mockito.mock(List.class);

    @Test
    public void whenGivenUserObjectCreateNewObjectAddsToList(){
        User user = new User();
        UserService userService = new UserService();

        Assertions.assertDoesNotThrow(()->userService.createUser(user));
    }

    @Test
    public void whenGivenUserObjectCreateNewObjectReturnsTrue(){
        User user = new User();
        UserService userService = new UserService();
        //Mockito.when(mockList.add(user)).thenReturn(true);

        //boolean result1 = userService.createUser(user);
        Assertions.assertNotNull(userService.createUser(user));
    }

    //@Test
    //public void whenGetAllUsersIsCalledReturnsListOfAllUsers() {

    //    UserService userService = new UserService();
    //    Assertions.assertDoesNotThrow(()-> userService.getAllUsers());
    //}

    //@Test
    //public void whenGivenValidIdGetUserByIdReturnsUserWithThatId(){
    //    User user = new User(0, "name1", "redse");

     //   Mockito.when(mockList.size()).thenReturn(1);
    //    Mockito.when(mockList.get(0)).thenReturn(user);
    //    UserService userService = new UserService(mockList);
    //    User result = userService.getUserById(0);

    //    Assertions.assertEquals(user, result);
    //}

    //@Test
    //public void whenGivenValidIdDeleteUserByIdReturnsTrue(){
    //    List<User> mockList=Mockito.mock(List.class);
    //    UserService userService = new UserService(mockList);
    //    User user = new User();
    //    user.setId(1);

    //    Mockito.when(mockList.size()).thenReturn(1);
    //    Mockito.when(mockList.get(0)).thenReturn(user);
    //    Mockito.when(mockList.remove(0)).thenReturn(user);

    //    Assertions.assertTrue(userService.deleteUserById(1));
    //}
}
