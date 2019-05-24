package com.wagner.demo_app.event;

import com.wagner.demo_app.model.AgeCategory;
import com.wagner.demo_app.model.EmploymentType;
import com.wagner.demo_app.model.Gender;

import java.util.EventObject;

public class FormEvent extends EventObject {

  private String         name;
  private String         occupation;
  private AgeCategory    ageCategory;
  private EmploymentType employmentType;
  private boolean        usCitizen;
  private String         taxID;
  private Gender         gender;

  public FormEvent(  Object source, String name, String occupation, AgeCategory ageCategory, EmploymentType employmentType
                   , boolean usCitizen, String taxID, Gender gender){
    super(source);
    this.name           = name;
    this.occupation     = occupation;
    this.ageCategory    = ageCategory;
    this.employmentType = employmentType;
    this.usCitizen      = usCitizen;
    this.taxID          = taxID;
    this.gender         = gender;
  }

  public String getName() {
    return name;
  }

  public String getOccupation() {
    return occupation;
  }

  public AgeCategory getAgeCategory() {
    return ageCategory;
  }

  public EmploymentType getEmploymentType() {
    return employmentType;
  }

  public boolean isUsCitizen() {
    return usCitizen;
  }

  public String getTaxID() {
    return taxID;
  }

  public Gender getGender() {
    return gender;
  }
}
