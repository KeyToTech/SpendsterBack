package domain.repositories.simple

import java.util

import data.entity.Category
import data.store.CategoryStore
import domain.repositories.CategoryRepository
import javax.inject.Inject

class SimpleCategoryRepository @Inject()(private val store: CategoryStore) extends CategoryRepository{
  override def findBy(id: String): Category = {
    val category = store.find(id)
    if(category == null) {
      throw new NoSuchElementException("There is no such object")
    }else {
      category
    }
  }

  override def delete(id: String): Boolean = {
    val category = store.find(id)
    if(category == null) {
      throw new NoSuchElementException("There is no such object")
    } else {
      store.delete(id)
    }
  }

  override def update(obj: Category): Category = {
    val category = store.find(obj.getId)
    if(category == null){
      throw new NoSuchElementException("There is no such object")
    }else{
      store.update(obj)
    }
  }

  override def save(obj: Category): Category = {
    val category = store.find(obj.getId)
    if(category != null){
      throw new IllegalArgumentException("Object already exists")
    }else{
      store.save(obj)
    }
  }

  override def getAll(userId: String): util.List[Category] = {
    store.getAll(userId)
  }
}
