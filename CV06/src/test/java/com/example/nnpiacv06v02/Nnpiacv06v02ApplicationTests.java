package com.example.nnpiacv06v02;

import com.example.nnpiacv06v02.shared.GenericResponse;
import com.example.nnpiacv06v02.user.User;
import com.example.nnpiacv06v02.user.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class NnpiaCv06ApplicationTests {

    public static final String API_1_0_USERS = "/api/1.0/users";
    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    UserRepository userRepository;

    @Before
    public  void cleanUp(){
        userRepository.deleteAll();
    }

    @Test
    void postUser_whenUserIsValid_receiveOk() {
        User user = createUser();
        ResponseEntity<Object> objectResponseEntity = testRestTemplate.postForEntity(API_1_0_USERS, user, Object.class);
        assertThat(objectResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void postUser_whenUserIsValid_userSavedToDatabase() {
        User user = createUser();
        ResponseEntity<Object> objectResponseEntity = testRestTemplate.postForEntity(API_1_0_USERS, user, Object.class);
        assertThat(userRepository.count()).isEqualTo(1);
    }

    @Test
    void postUser_whenUserIsValid_passwordIsHashed() {
        User user = createUser();
        ResponseEntity<GenericResponse> objectResponseEntity = testRestTemplate.postForEntity(API_1_0_USERS, user, GenericResponse.class);
        List<User> users = userRepository.findAll();
        User inDb = users.get(0);
        assertThat(inDb.getPassword()).isNotEqualToIgnoringCase(user.getPassword());
    }

    @Test
    void postUser_whenUserNameIsNull_receiveBadRequest() {
        User user = createUser();
        user.setUserName(null);
        ResponseEntity<GenericResponse> response = testRestTemplate.postForEntity(API_1_0_USERS, user, GenericResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void postUser_whenPasswordIsNull_receiveBadRequest() {
        User user = createUser();
        user.setPassword(null);
        ResponseEntity<GenericResponse> response = testRestTemplate.postForEntity(API_1_0_USERS, user, GenericResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void postUser_whenDisplayNameIsNull_receiveBadRequest() {
        User user = createUser();
        user.setDisplayName(null);
        ResponseEntity<GenericResponse> response = testRestTemplate.postForEntity(API_1_0_USERS, user, GenericResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void postUser_whenMailIsNotValid_receiveBadRequest() {
        User user = createUser();
        user.setEmail("mail");
        ResponseEntity<GenericResponse> response = testRestTemplate.postForEntity(API_1_0_USERS, user, GenericResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void postUser_whenUsernameIsTooShort_receiveBadRequest() {
        User user = createUser();
        user.setUserName("u");
        ResponseEntity<GenericResponse> response = testRestTemplate.postForEntity(API_1_0_USERS, user, GenericResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void postUser_whenUsernameIsTooLong_receiveBadRequest() {
        User user = createUser();
        user.setUserName("usernameusernameusernameusernameusernameusernameusernameusernameusernameusernameusernameusernameusernameusernameusernameusernameusername");
        ResponseEntity<GenericResponse> response = testRestTemplate.postForEntity(API_1_0_USERS, user, GenericResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void postUser_whenInvalidPhone_receiveBadRequest() {
        User user = createUser();
        user.setPhone("88");
        ResponseEntity<GenericResponse> response = testRestTemplate.postForEntity(API_1_0_USERS, user, GenericResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    //TODO ošetření vhodných hodnot např. min a max, délka uživatelského jména a hesla, slozeni hesla apod
    //Min, Max, Pattern
    //Délka jmena a hesla uvažuje s minimen v DB
    //beanvalidation.org
    private User createUser() {
        User user = new User();
        user.setUserName("test-user");
        user.setDisplayName("test-user");
        user.setPassword("1234");
        user.setPhone("777 777 777");
        user.setEmail("mail@mail.cz");
        return user;
    }

}
