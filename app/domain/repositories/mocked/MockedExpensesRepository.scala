package domain.repositories.mocked

import java.util
import java.util.Date
import java.util.UUID.randomUUID

import domain.entity.Expenses
import domain.repositories.ExpensesRepository

class MockedExpensesRepository extends ExpensesRepository{

  override def getOne(id: String): Expenses = {
    val obj = new Expenses
    obj.setId(id)
    obj.setAmount(0.7)
    obj.setCategoryId(randomUUID().toString)
    obj.setCreatedDate(new Date())

    obj
  }

  override def getAll: util.List[Expenses] = {
    val obj = new Expenses
    obj.setId(randomUUID().toString)
    obj.setAmount(0.7)
    obj.setCategoryId(randomUUID().toString)
    obj.setCreatedDate(new Date())

    val list = new util.ArrayList[Expenses]()
    list.add(obj)
    list.add(obj)
    list.add(obj)

    list
  }

  override def update(obj: Expenses): Expenses = {
    obj
  }

  override def save(obj: Expenses): Expenses = {
    obj.setId(randomUUID().toString)
    obj.setCreatedDate(new Date())
    obj
  }

  override def delete(id: String): Expenses = {
    val obj = new Expenses
    obj.setId(id)
    obj.setAmount(0.7)
    obj.setCategoryId(randomUUID().toString)
    obj.setCreatedDate(new Date())

    obj
  }
}
