package domain.repositories.mocked

import java.util
import java.util.Date

import domain.entity.Category
import domain.repositories.CategoryRepository

class MockedCategoryRepository extends CategoryRepository{

  override def save(obj: Category): Category = {
    obj
  }

  override def getOne(id: String): Category = {
    val obj = new Category
    obj.setId(id)
    obj.setName("mockedName")
    obj.setType("mockedType")
    obj.setCreatedDate(new Date())

    obj
  }

  override def getAll: util.List[Category] = {
    val obj = new Category
    obj.setId("mockedId")
    obj.setName("mockedName")
    obj.setType("mockedType")
    obj.setCreatedDate(new Date())

    val list = new util.ArrayList[Category]()
    list.add(obj)
    list.add(obj)
    list.add(obj)
    list
  }

  override def update(obj: Category): Category = {
    obj
  }

  override def delete(id: String): Category = {
    val obj = new Category
    obj.setId(id)
    obj.setName("mockedName")
    obj.setType("mockedType")
    obj.setCreatedDate(new Date())

    obj
  }
}
