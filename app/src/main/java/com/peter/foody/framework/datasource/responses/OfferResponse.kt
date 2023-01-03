package com.peter.foody.framework.datasource.responses

data class OfferResponse(
    var name: String,
    var RestauranthId: String,
    var description: String,
    var cover: String,
    var cuisines: List<CuisineResponse>
)
