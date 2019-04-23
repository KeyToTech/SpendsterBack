package domain.repositories.mocked

import java.util
import java.util.Date
import java.util.UUID.randomUUID

import domain.entity.Expenses
import domain.repositories.ExpensesRepository

class MockedExpensesRepository extends ExpensesRepository{

  override def findBy(id: String): Expenses = {
    new Expenses(id, 0.7, randomUUID().toString, new Date)
  }

  override def getAll: util.List[Expenses] = {
    val obj = new Expenses(randomUUID().toString, 0.7, randomUUID().toString, new Date)

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
    new Expenses(randomUUID().toString, obj.getAmount, obj.getCategoryId, new Date)
  }

  override def delete(id: String): Boolean = {
    true
  }
}
