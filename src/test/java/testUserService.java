import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;
import model.services.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Created by User on 4/24/2017.
 */
public class testUserService {

    @Mock
    private DaoFactory factory;
    @Mock
    private UserDao userDao;

    private UserService userService;
    private User user;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        when(factory.createUserDao(any())).thenReturn(userDao);
        userService = new UserService();
        ((UserService)userService).setDaoFactory(factory);
        user = new User.Builder()
                .setId(1)
                .setName("John")
                .setEmail("John@gmail.com")
                .setAdmin(true)
                .setBlocked(false)
                .build();
    }

    @Test
    public void testFindByID(){
        when(userDao.findById(anyInt())).thenReturn(Optional.of(user));
        Optional result = userService.getById(1);
        verify(userDao, times(1)).findById(1);
        Assert.assertEquals(user, result.get());
    }

    @Test
    public void testCreateUser(){
        doNothing().when(userDao).create(any());
        when(userDao.getUserByEmail(user.getEmail())).thenReturn(Optional.empty());
        userService.create(user);
        verify(userDao, times(1)).create(user);
    }
}


