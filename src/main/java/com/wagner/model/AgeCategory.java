package com.wagner.model;

public enum AgeCategory {
  CHILD("Child"), ADULT("Adult"), SENIOR("Senior"), NOT_DEFINED("Not defined");
  String value;

  AgeCategory(String value){
    this.value = value;
  }


  @Override
  public String toString() {
    return this.value;
  }
}

