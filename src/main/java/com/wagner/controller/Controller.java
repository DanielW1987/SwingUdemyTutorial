package com.wagner.controller;

import com.wagner.event.FormEvent;
import com.wagner.model.Database;
import com.wagner.model.Person;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Controller {
  ////////////////////// region Variables  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  private Database database;
  // endregion
  ////////////////////// region Initialisation /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public Controller(){
    this.database = new Database();
  }
  // endregion
  ////////////////////// region Methods ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public void addPerson(FormEvent formEvent){
    Person person = new Person(  formEvent.getName()
                               , formEvent.getOccupation()
                               , formEvent.getAgeCategory()
                               , formEvent.getEmploymentType()
                               , formEvent.isUsCitizen()
                               , formEvent.getTaxID()
                               , formEvent.getGender());

    database.addPerson(person);
  }

  public List<Person> getPersons(){
    return database.getPersons();
  }

  public Person removePerson(int index){
    return database.removePerson(index);
  }

  public void saveToFile(File file) throws IOException{
    this.database.saveToFile(file);
  }

  public void loadFromFile(File file) throws IOException{
    this.database.loadFromFile(file);
  }
  // endregion
  ////////////////////// region Inner Classes //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////// End of Class //////////////////////////////////////////////////////////////////////////////////////////////////////////////// endregion
}

