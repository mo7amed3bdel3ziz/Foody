package com.peter.foody.business.model

data class DataModel<T>(
   // var categories: List<Category>,
   // var offers: List<Offer>,
   // var sliders: List<Slider>
    var State :Int,
    var data :List<T>,
    var Message :String,
    val Item : T?
)