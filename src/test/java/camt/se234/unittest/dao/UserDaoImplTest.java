package camt.se234.unittest.dao;
import camt.se234.unittest.entity.User;
import camt.se234.unittest.exception.OldDateException;
import camt.se234.unittest.service.UserServiceImpl;
import org.hamcrest.Matcher;
import org.hamcrest.core.Is;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.time.LocalDate;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;


public class UserDaoImplTest {
    @Test
    public void testGetUsers(){
        UserDaoImpl userDao = new UserDaoImpl();
        assertThat(userDao.getUsers(),
                hasItems(new User("Prayuth","1234","Tu",
                                LocalDate.of(1979,2,14),"08612345678"),
                        new User("Tucky","5675","Tuckung",
                                LocalDate.of(1999,8,30),"08687654321"),
                        new User("Honey","aabbcc","Honey",
                                LocalDate.of(2012,11,13),"0000000000"),
                        new User("None","none","NoName",
                                LocalDate.of(2112,1,1),"9999999999")
                ));
    }


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testLoginException(){
        UserServiceImpl userService = new UserServiceImpl();
        UserDaoImpl userDao = new UserDaoImpl();
        userService.setUserDao(userDao);
        // check for the exception we expect
        thrown.expect(NullPointerException.class);
        userService.login("", "");

        thrown.expect(RuntimeException.class);
        thrown.expectMessage("User name must not contain special characters");
        userService.login("abcd*", "1234");

    }


    @Test
    public void testLogin(){
        UserServiceImpl userService = new UserServiceImpl();
        UserDaoImpl userDao =new UserDaoImpl();
        userService.setUserDao(userDao);
        assertThat(userService.login("Prayuth","1234"),is(new User("Prayuth","1234","Tu",
                LocalDate.of(1979,2,14),"08612345678")));
    }

    @Test
    public void testIsAbleGoToPub(){
        UserServiceImpl userService = new UserServiceImpl();
        UserDaoImpl userDao =new UserDaoImpl();
        userService.setUserDao(userDao);
        assertThat(userService.isAbleToGoToPub(new User("Prayuth","1234","Tu",
                LocalDate.of(1979,2,14),"08612345678"),LocalDate.now()),is(true));

        assertThat(userService.isAbleToGoToPub(new User("Honey","aabbcc","Honey",
                LocalDate.of(2012,11,13),"0000000000"),LocalDate.now()),is(false));

        thrown.expect(OldDateException.class);
        assertThat(userService.isAbleToGoToPub(new User("Honey","aabbcc","Honey",
                LocalDate.of(2060,11,13),"0000000000"),LocalDate.now()),is(false));

    }

    @Test
    public void testGetPubAllowanceUser(){
        UserServiceImpl userService = new UserServiceImpl();
        UserDaoImpl userDao =new UserDaoImpl();
        userService.setUserDao(userDao);
        assertThat(userService.getPubAllowanceUser(LocalDate.now()),containsInAnyOrder(new User("Prayuth","1234","Tu",
                LocalDate.of(1979,2,14),"08612345678")));

       /* assertThat(userService.getPubAllowanceUser(LocalDate.now()),containsInAnyOrder());*/  //if anyone lessthan 20

    }



}
