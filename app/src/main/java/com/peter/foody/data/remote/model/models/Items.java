package com.peter.foody.data.remote.model.models;



public class Items
      //  implements Searchable
{
    String name;
    Double price;
    Double Tax;
    Double Total=0.0;


    public Items() {
    }

    public Items(String name, Double price) {
        this.name = name;
        this.price = price;
        this.Tax = (price*14.0)/100;
       this.Total = price+Tax;
    }

  //  @Override
  //  public String getTitle() {
  //      return name;
  //  }
  //  public Double getprice() {
  //      return price;
  //  }
  //  public Double getTax() {
  //      return Tax;
  //  }
  //  public Double getTotal() {
  //      return Total;
  //  }
}
