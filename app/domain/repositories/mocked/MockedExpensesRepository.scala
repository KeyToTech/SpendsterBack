package domain.repositories.mocked

import java.util
import java.util.Date
import java.util.UUID.randomUUID

import domain.entity.Expenses
import domain.repositories.ExpensesRepository

import scala.util.Random

class MockedExpensesRepository extends ExpensesRepository{

  override def findBy(id: String): Expenses = {
    new Expenses(id, 0.7, "mockedNote", randomUUID().toString, new Date)
  }

  override def getByRange(start: Date, end: Date): util.List[Expenses] = {
    val dateDiff = (end.getTime - start.getTime).toInt

    val randomGenerator = new Random
    val list = new util.ArrayList[Expenses]()

    for(_ <- 0 to 50){
      val date = new Date(start.getTime + randomGenerator.nextInt(dateDiff))
      val obj = new Expenses(randomUUID().toString, 0.7, "mockedNote", randomUUID().toString, date)
      list.add(obj)
    }

    list
  }

  override def update(obj: Expenses): Expenses = {
    obj
  }

  override def save(obj: Expenses): Expenses = {
    new Expenses(randomUUID().toString, obj.getAmount, "mockedNote", obj.getCategoryId, new Date)
  }

  override def delete(id: String): Boolean = {
    true
  }
}
