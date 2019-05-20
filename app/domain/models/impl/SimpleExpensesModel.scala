package domain.models.impl

import java.util.Date
import java.util

import com.google.gson.Gson
import data.entity.Expenses
import domain.models.ExpensesModel
import domain.repositories.ExpensesRepository
import javax.inject.Inject

case class RExpenses(
                    id: String,
                    userId: String,
                    amount: Double,
                    note: String,
                    categoryId: String,
                    createdDate: Long
                    )

class SimpleExpensesModel @Inject()(repo: ExpensesRepository,
                                    gson: Gson)
  extends ExpensesModel{

  private def mapExpenses(expenses: Expenses): RExpenses = {
    RExpenses(expenses.getId, expenses.getUserId, expenses.getAmount, expenses.getNote, expenses.getCategoryId, expenses.getCreatedDate.getTime)
  }

  override def getByRange(userId: String, start: Date, end: Date): String = {
    val rExpensesList = new util.ArrayList[RExpenses]
    repo.getByRange(userId, start, end)
      .forEach(expenses => {
        rExpensesList.add(mapExpenses(expenses))
      })
    gson.toJson(rExpensesList)
  }

  override def update(obj: Expenses): String = {
    val expenses = repo.update(obj)
    gson.toJson(mapExpenses(expenses))
  }

  override def save(obj: Expenses): String = {
    val expenses = repo.save(obj)
    gson.toJson(mapExpenses(expenses))
  }

  override def findBy(id: String): String = {
    val expenses = repo.findBy(id)
    gson.toJson(mapExpenses(expenses))
  }

  override def delete(id: String): Boolean = {
    repo.delete(id)
  }
}
