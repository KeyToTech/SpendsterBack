package domain.models.simple

import java.util

import com.google.gson.Gson
import data.entity.Category
import domain.models.CategoryModel
import domain.models.responseEntities.RCategory
import domain.repositories.CategoryRepository
import javax.inject.Inject

class SimpleCategoryModel @Inject()(repo: CategoryRepository,
                                    gson: Gson)
  extends CategoryModel{

  override def getAll(userId: String): String = {
    val rCategoryList = new util.ArrayList[RCategory]
    repo.getAll(userId)
      .forEach(category =>{
        rCategoryList.add(new RCategory(category))
      })
    gson.toJson(rCategoryList)
  }

  override def update(obj: Category): String = {
    val category = repo.update(obj)
    gson.toJson(new RCategory(category))
  }

  override def save(obj: Category): String = {
    val category = repo.save(obj)
    gson.toJson(new RCategory(category))
  }

  override def findBy(id: String): String = {
    val category = repo.findBy(id)
    gson.toJson(new RCategory(category))
  }

  override def delete(id: String): Boolean = {
    repo.delete(id)
  }
}
