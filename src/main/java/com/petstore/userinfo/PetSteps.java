package com.petstore.userinfo;

import com.petstore.constant.EndPoints;
import com.petstore.model.PetPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class PetSteps {
    static int id;
@Step("Creating pet detail with id:{0},name:{1},photourl: {2},status :{3}")
        public ValidatableResponse addNewPet(String name,String photourl,String status) {
    PetPojo petPojo = new PetPojo();
    petPojo.setId(id);
petPojo.setName(name);
petPojo.setPhotourl(photourl);
petPojo.setStatus(status);
   return  SerenityRest.given()
            .contentType(ContentType.JSON)
            .when()
            .body(petPojo)
            .post()
            .then().log().all().statusCode(201);
}
@Step( "Getting pet by id")
    public  ValidatableResponse GetPetById(int id){

        String s1 = "findAll{it.id == '";
        String s2 = "'}.get(0)";
        return SerenityRest.given()
                .when()
                .get(EndPoints.FindPetById)
                .then().statusCode(200)
                .extract()
                .path(s1 + id + s2);

    }

}
