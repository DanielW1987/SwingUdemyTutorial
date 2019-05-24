package com.wagner.demo_app.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Database {
  ////////////////////// region Variables  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  private List<Person> persons;
  // endregion
  ////////////////////// region Initialisation /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public Database(){
    this.persons = new ArrayList<>();

    // add demo person
    this.persons.add(new Person("Daniel", "Java", AgeCategory.ADULT, EmploymentType.EMPLOYED, false, "", Gender.MALE));
  }
  // endregion
  ////////////////////// region Methods ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public void addPerson(Person p){
    this.persons.add(p);
  }

  public List<Person> getPersons(){
    return Collections.unmodifiableList(this.persons);
  }

  public Person removePerson(int index){
    return this.persons.remove(index);
  }

  public void saveToFile(File file) throws IOException{
    try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
      Person[] persons = this.getPersons().toArray(new Person[this.persons.size()]);
      oos.writeObject(persons);
    }
  }

  public void loadFromFile(File file) throws IOException{
    try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
      Person[] persons = (Person[]) ois.readObject();
      this.persons.clear();
      this.persons.addAll(Arrays.asList(persons));
    }
    catch(ClassNotFoundException cnfe){
      cnfe.printStackTrace();
    }
  }
  // endregion
  ////////////////////// region Inner Classes //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////// End of Class //////////////////////////////////////////////////////////////////////////////////////////////////////////////// endregion
}