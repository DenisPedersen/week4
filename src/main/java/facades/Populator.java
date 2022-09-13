/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.PersonDTO;
import dtos.RenameMeDTO;
import entities.Person;
import entities.RenameMe;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade personFacade = PersonFacade.getPersonFacade(emf);
        personFacade.create(new PersonDTO("Frida", 44));
        personFacade.create(new PersonDTO("Bjarne", 5));
        personFacade.create(new PersonDTO("Arne", 12));
    }
    
    public static void main(String[] args) {
        populate();
    }
}
