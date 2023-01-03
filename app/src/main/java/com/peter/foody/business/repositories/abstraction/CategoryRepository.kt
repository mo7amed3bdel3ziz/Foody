package com.peter.foody.business.repositories.abstraction

import com.peter.foody.business.model.foods.CategoryModel
import com.peter.foody.business.model.foods.PostCategoryModel
import com.peter.foody.business.usecases.State
import com.peter.foody.framework.datasource.responses.TaskAPI
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun GetCategories( ComID: String): Flow<State<TaskAPI<CategoryModel>>>
    fun  PostCategory( list: PostCategoryModel): Flow<State<TaskAPI<PostCategoryModel>>>
}