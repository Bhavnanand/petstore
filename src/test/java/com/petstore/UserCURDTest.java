package com.petstore;

import com.petstore.userinfo.UserSteps;
import com.petstore.utils.TestUtils;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import testbase.TestBase;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class UserCURDTest extends TestBase {
    static String firstName="Hina"+TestUtils.getRandomValue();
    static String lastName="Doshi"+TestUtils.getRandomValue();
    static String email="Hinadoshi123@gmail.com"+ TestUtils.getRandomValue();
    static String password ="hina123"+TestUtils.getRandomValue();
    static String phone ="0208776621";
    static int status =0;
    static int userid;
    UserSteps userSteps= new UserSteps();

    @Title("This will create a new user")
    @Test
    public void test001() {
        UserSteps userSteps= new UserSteps();
        userSteps.CreateUser(firstName,lastName,email,password,status);
    }
/*
    @Title("Verify if the user was added to the application")
    @Test
    public void test002() {
        String s1 = "findAll{it.firstName == '";
        String s2 = "'}.get(0)";
        HashMap<String, Object> studentMap = SerenityRest.given()
                .when()
                .get(EndPoints.CreateAUser)
                .then().statusCode(200)
                .extract()
                .path(s1 + firstName + s2);
        Assert.assertThat(studentMap, hasValue(firstName));
        userid = (int) studentMap.get("id");
    }*/
    @Title("This will read user information for firstName{0}")
@Test
    public void GetUserInfoByFirstName(String firstName){
        userSteps.GetUserByFirstName(firstName);

    }
    @Title("Update the user information and verify the updated information")
    @Test
    public void test003() {
        firstName=firstName+"updated";
        userSteps.UpdateUserByFirstName(firstName,lastName,email,password,phone,status);
        HashMap<String,Object>userMap =userSteps.GetUserByFirstName(firstName);
        Assert.assertThat(userMap, hasValue(firstName));
    }
    @Title("Delete the student and verify if the student is deleted!")
    @Test
    public void test004() {
        userSteps.deleteUser(firstName).statusCode(204);
        userSteps.getUserByFirstName(firstName).statusCode(404);
    }
}
