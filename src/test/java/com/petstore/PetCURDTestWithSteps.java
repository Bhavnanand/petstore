package com.petstore;

import com.petstore.model.PetPojo;
import org.junit.Test;
import testbase.TestBase;



public class PetCURDTestWithSteps extends TestBase {
    PetPojo petPojo= new PetPojo();
    @Test
    public void AddNewPet(String name,String photourl ,String status){
        PetPojo petPojo= new PetPojo();
        petPojo.addnewpet(name,photourl,status);
    }
    @Test
    public void FindPetById(int id){
        petPojo.GetPetById( id);
    }
}
