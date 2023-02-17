package com.peter.foody.data.remote.model.models;


public class CompanyModel
      //  implements Searchable
{
    public String ComID;
    public String Name;

//    public CompanyModel(String comID, String name) {
//        ComID = comID;
//        Name = name;
//    }

    public String getComID() {
        return ComID;
    }

    public void setComID(String comID) {
        ComID = comID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

  //  @Override
  //  public String getTitle() {
  //      return getName();
  //  }
  //  public String getID() {
  //      return ComID;
  //  }


}
