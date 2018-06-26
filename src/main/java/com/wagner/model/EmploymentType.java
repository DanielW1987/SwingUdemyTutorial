package com.wagner.model;

public enum EmploymentType {
  EMPLOYED("Employed"), SELF_EMPLOYED("Self-employed"), UNEMPLOYED("unemployed"), NOT_DEFINED("Not defined");

  private String value;
  EmploymentType(String value){
    this.value = value;
  }


  @Override
  public String toString() {
    return this.value;
  }
}
