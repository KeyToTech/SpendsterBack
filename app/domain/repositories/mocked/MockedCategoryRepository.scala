package domain.repositories.mocked

import java.util
import java.util.Date
import java.util.UUID.randomUUID

import data.entity.Category
import domain.repositories.CategoryRepository

class MockedCategoryRepository extends CategoryRepository{

  override def save(obj: Category): Category = {
    obj
  }

  override def findBy(id: String): Category = {
    new Category(id, "mockedUserId", "mockedName", "mockedType", new Date())
  }

  override def getAll: util.List[Category] = {
    val list = new util.ArrayList[Category]()

    for(_ <- 0 to 50){
      val obj = new Category(randomUUID().toString, "mockedUserId", "mockedName", "mockedType", new Date())
      list.add(obj)
    }
    
    list
  }

  override def update(obj: Category): Category = {
    obj
  }

  override def delete(id: String): Boolean = {
    true
  }
}
