package domain.models.impl

import com.google.gson.Gson
import domain.entity.Category
import domain.models.CategoryModel
import domain.repositories.CategoryRepository
import javax.inject.Inject

class SimpleCategoryModel @Inject()(repo: CategoryRepository,
                                    gson: Gson)
  extends CategoryModel{

  override def getAll: String = {
    gson.toJson(repo.getAll)
  }

  override def update(obj: Category): String = {
    gson.toJson(repo.update(obj))
  }

  override def save(obj: Category): String = {
    gson.toJson(repo.save(obj))
  }

  override def findBy(id: String): String = {
    gson.toJson(repo.findBy(id))
  }

  override def delete(id: String): String = {
    gson.toJson(repo.delete(id))
  }
}
