package com.wagner.model;

import java.io.Serializable;

public class Person implements Serializable{

  private static final long serialVersionUID = 1L;
  private static int nextId = 0;

  ////////////////////// region Variables  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  private int            id;
  private String         name;
  private String         occupation;
  private AgeCategory    ageCategory;
  private EmploymentType employmentType;
  private boolean        usCitizen;
  private String         taxID;
  private Gender         gender;
  // endregion
  ////////////////////// region Initialisation /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public Person(  String name, String occupation, AgeCategory ageCategory, EmploymentType employmentType
                , boolean usCitizen, String taxID, Gender gender) {
    this.id             = ++nextId;
    this.name           = name;
    this.occupation     = occupation;
    this.ageCategory    = ageCategory;
    this.employmentType = employmentType;
    this.usCitizen      = usCitizen;
    this.taxID          = taxID;
    this.gender         = gender;
  }
  // endregion
  ////////////////////// region Methods ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


  @Override
  public String toString() {
    return this.name + ", " + this.gender + ", " + this.occupation + ", " + this.ageCategory +
                       ", " + this.employmentType + ", " + usCitizen + ", " + taxID;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOccupation() {
    return occupation;
  }

  public void setOccupation(String occupation) {
    this.occupation = occupation;
  }

  public AgeCategory getAgeCategory() {
    return ageCategory;
  }

  public void setAgeCategory(AgeCategory ageCategory) {
    this.ageCategory = ageCategory;
  }

  public EmploymentType getEmploymentType() {
    return employmentType;
  }

  public void setEmploymentType(EmploymentType employmentType) {
    this.employmentType = employmentType;
  }

  public boolean isUsCitizen() {
    return usCitizen;
  }

  public void setUsCitizen(boolean usCitizen) {
    this.usCitizen = usCitizen;
  }

  public String getTaxID() {
    return taxID;
  }

  public void setTaxID(String taxID) {
    this.taxID = taxID;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }
  // endregion
  ////////////////////// region Inner Classes //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////// End of Class //////////////////////////////////////////////////////////////////////////////////////////////////////////////// endregion
}

