package com.peter.foody.business.model

data class Offer(
    var name: String,
    var restaurantID: String,
    var description: String,
    var cover: String,
   // var cuisines: List<Cuisine>
) {
  // fun getCuisinesString(): String {
  //     var result: String = ""
  //     repeat(cuisines.size) {
  //         result = if (it != 0 && it != cuisines.size)
  //             result.plus(" - ${cuisines[it].name}")
  //         else
  //             result.plus(cuisines[it].name)
  //     }


  //     return result
  // }
}
