package com.wagner.employee_app.model.employee;

public enum EmployeeDepartment {

    HR("HR")
  , ENGINEERING("Engineering")
  , LEGAL("Legal")
  , CONSULTING("Consulting");

  private String name;

  EmployeeDepartment(String name){
    this.name = name;
  }

  public String getName(){
    return this.name;
  }

  public static EmployeeDepartment ofName(String name){
    switch(name){
      case "HR":
        return HR;
      case "Engineering":
        return ENGINEERING;
      case "Legal":
        return LEGAL;
      case "Consulting":
        return CONSULTING;
      default:
        throw new IllegalArgumentException("snh: name is unknown!");
    }
  }
}
