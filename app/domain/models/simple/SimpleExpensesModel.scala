package domain.models.simple

import java.util.Date
import java.util

import com.google.gson.Gson
import data.entity.Expenses
import domain.models.ExpensesModel
import domain.models.responseEntities.RExpenses
import domain.repositories.ExpensesRepository
import javax.inject.Inject

class SimpleExpensesModel @Inject()(repo: ExpensesRepository,
                                    gson: Gson)
  extends ExpensesModel{

  override def getByRange(userId: String, start: Date, end: Date, offset: Int, limit: Int): String = {
    val rExpensesList = new util.ArrayList[RExpenses]
    repo.getByRange(userId, start, end, offset, limit)
      .forEach(expenses => {
        rExpensesList.add(new RExpenses(expenses))
      })
    gson.toJson(rExpensesList)
  }

  override def update(obj: Expenses): String = {
    val expenses = repo.update(obj)
    gson.toJson(new RExpenses(expenses))
  }

  override def save(obj: Expenses): String = {
    val expenses = repo.save(obj)
    gson.toJson(new RExpenses(expenses))
  }

  override def findBy(id: String): String = {
    val expenses = repo.findBy(id)
    gson.toJson(new RExpenses(expenses))
  }

  override def delete(id: String): Boolean = {
    repo.delete(id)
  }
}
