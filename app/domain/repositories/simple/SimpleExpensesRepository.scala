package domain.repositories.simple

import java.util
import java.util.Date

import data.entity.Expenses
import data.store.ExpensesStore
import domain.repositories.ExpensesRepository
import javax.inject.Inject

class SimpleExpensesRepository @Inject()(private val store: ExpensesStore) extends ExpensesRepository{
  override def getByRange(userId: String, start: Date, end: Date, offset: Int, limit: Int): util.List[Expenses] = {
    store.findRange(userId, start, end, offset, limit)
  }

  override def delete(id: String): Boolean = {
    val expenses = store.find(id)
    if(expenses == null) {
      throw new NoSuchElementException("There is no such object")
    }
    store.delete(id)
  }

  override def findBy(id: String): Expenses = {
    val expenses = store.find(id)
    if(expenses == null){
      throw new NoSuchElementException("There is no such object")
    }else {
      expenses
    }
  }

  override def update(obj: Expenses): Expenses = {
    val expenses = store.find(obj.getId)
    if(expenses == null){
      throw new NoSuchElementException("There is no such object")
    }else {
      store.update(obj)
    }
  }

  override def save(obj: Expenses): Expenses = {
    val expenses = store.find(obj.getId)
    if(expenses != null){
      throw new IllegalArgumentException("Object already exists")
    }else {
      store.save(obj)
    }
  }
}
