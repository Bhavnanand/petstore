package com.petstore.userinfo;

import com.petstore.constant.EndPoints;
import com.petstore.model.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class UserSteps {
    static int userid;
@Step ("Creating student with firstname: firstname{0},lastName : lastName{1},email :email{2}.password :password{3},status :status{4}")
        public ValidatableResponse  CreateUser(String firstName, String lastName, String email, String password, int status){
UserPojo userPojo =new UserPojo();
userPojo.setFirstName(firstName);
userPojo.setLastName(lastName);
userPojo.setEmail(email);
userPojo.setPassword(password);
userPojo.setUserStatus(status);
   return  SerenityRest.given()
                .contentType(ContentType.JSON)
                .when()
                .body(userPojo)
                .post()
                .then().log().all().statusCode(201);
}
@Step("Getting  user information using firstname :firstName{0}" )
public HashMap<String,Object> GetUserByFirstName(String firstName) {
    String s1 = "findAll{it.firstName == '";
    String s2 = "'}.get(0)";
   return SerenityRest.given()
            .when()
            .get(EndPoints.GetUserByUsername)
            .then().statusCode(200)
            .extract()
            .path(s1 + firstName + s2);

}
@Step
    public ValidatableResponse UpdateUserByFirstName(String firstName,String lastName,String email,String password,String phone, int status)
{
    UserPojo userPojo =new UserPojo();
    userPojo.setFirstName(firstName);
    userPojo.setLastName(lastName);
    userPojo.setEmail(email);
    userPojo.setPassword(password);
    userPojo.setUserStatus(status);
   return  SerenityRest.given()
            .header("ContentType","application/json")
            .pathParam("FirstName","firstName")
            .body(userPojo)
            .when()
            .put(EndPoints.UpdateUserByUsername)
            .then();
}
    @Step("Deleting user information with firstName: {0}")
    public ValidatableResponse deleteUser(String firstName) {
        return SerenityRest.given()
                .pathParam("firstName", firstName)
                .when()
                .delete(EndPoints.DeleteUserByUsername)
                .then();
    }

    @Step("Getting student information with studentId : {0}")
    public ValidatableResponse getUserByFirstName(String firstName ) {
        return SerenityRest.given()
                .pathParam("FirstName", firstName)
                .when()
                .get(EndPoints.GetUserByUsername)
                .then();
    }



}
