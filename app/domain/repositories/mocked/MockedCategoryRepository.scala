package domain.repositories.mocked

import java.util
import java.util.Date
import java.util.UUID.randomUUID

import domain.entity.Category
import domain.repositories.CategoryRepository

class MockedCategoryRepository extends CategoryRepository{

  override def save(obj: Category): Category = {
    obj
  }

  override def findBy(id: String): Category = {
    new Category(id, "mockedName", "mockedType", new Date())
  }

  override def getAll: util.List[Category] = {
    val obj = new Category(randomUUID().toString, "mockedName", "mockedType", new Date())

    val list = new util.ArrayList[Category]()
    list.add(obj)
    list.add(obj)
    list.add(obj)
    list
  }

  override def update(obj: Category): Category = {
    obj
  }

  override def delete(id: String): Boolean = {
    true
  }
}
