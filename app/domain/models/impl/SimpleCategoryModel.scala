package domain.models.impl

import java.util.Date

import com.google.gson.Gson
import data.entity.Category
import domain.models.CategoryModel
import domain.repositories.CategoryRepository
import javax.inject.Inject
import java.util

case class RCategory(
                    id: String,
                    userId: String,
                    name: String,
                    categoryType: String,
                    icon: String,
                    createdDate: Date
                    )

class SimpleCategoryModel @Inject()(repo: CategoryRepository,
                                    gson: Gson)
  extends CategoryModel{

  override def getAll(userId: String): String = {
    val rCategoryList = new util.ArrayList[RCategory]
    repo.getAll(userId)
      .forEach(category =>{
        rCategoryList.add(RCategory(category.getId, category.getUserId, category.getName, category.getType, category.getIcon, category.getCreatedDate))
      })
    gson.toJson(rCategoryList)
  }

  override def update(obj: Category): String = {
    val category = repo.update(obj)
    gson.toJson(RCategory(category.getId, category.getUserId, category.getName, category.getType, category.getIcon, category.getCreatedDate))
  }

  override def save(obj: Category): String = {
    val category = repo.save(obj)
    gson.toJson(RCategory(category.getId, category.getUserId, category.getName, category.getType, category.getIcon, category.getCreatedDate))
  }

  override def findBy(id: String): String = {
    val category = repo.findBy(id)
    gson.toJson(RCategory(category.getId, category.getUserId, category.getName, category.getType, category.getIcon, category.getCreatedDate))
  }

  override def delete(id: String): Boolean = {
    repo.delete(id)
  }
}
