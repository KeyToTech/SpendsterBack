package domain.models.simple

import java.util

import com.google.gson.Gson
import data.entity.Category
import domain.models.CategoryModel
import domain.repositories.CategoryRepository
import javax.inject.Inject

case class RCategory(
                    id: String,
                    userId: String,
                    name: String,
                    categoryType: String,
                    icon: String,
                    createdDate: Long
                    )

class SimpleCategoryModel @Inject()(repo: CategoryRepository,
                                    gson: Gson)
  extends CategoryModel{

  private def mapCategory(category: Category): RCategory = {
    RCategory(category.getId, category.getUserId, category.getName, category.getType, category.getIcon, category.getCreatedDate.getTime)
  }

  override def getAll(userId: String): String = {
    val rCategoryList = new util.ArrayList[RCategory]
    repo.getAll(userId)
      .forEach(category =>{
        rCategoryList.add(mapCategory(category))
      })
    gson.toJson(rCategoryList)
  }

  override def update(obj: Category): String = {
    val category = repo.update(obj)
    gson.toJson(mapCategory(category))
  }

  override def save(obj: Category): String = {
    val category = repo.save(obj)
    gson.toJson(mapCategory(category))
  }

  override def findBy(id: String): String = {
    val category = repo.findBy(id)
    gson.toJson(mapCategory(category))
  }

  override def delete(id: String): Boolean = {
    repo.delete(id)
  }
}
