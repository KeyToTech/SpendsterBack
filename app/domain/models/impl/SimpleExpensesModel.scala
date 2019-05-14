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
                    createdDate: Date
                    )

class SimpleExpensesModel @Inject()(repo: ExpensesRepository,
                                    gson: Gson)
  extends ExpensesModel{

  override def getByRange(userId: String, start: Date, end: Date): String = {
    val rExpensesList = new util.ArrayList[RExpenses]
    repo.getByRange(userId, start, end)
      .forEach(expenses => {
        rExpensesList.add(RExpenses(expenses.getId, expenses.getUserId, expenses.getAmount, expenses.getNote, expenses.getCategoryId, expenses.getCreatedDate))
      })
    gson.toJson(rExpensesList)
  }

  override def update(obj: Expenses): String = {
    val expenses = repo.update(obj)
    gson.toJson(RExpenses(expenses.getId, expenses.getUserId, expenses.getAmount, expenses.getNote, expenses.getCategoryId, expenses.getCreatedDate))
  }

  override def save(obj: Expenses): String = {
    val expenses = repo.save(obj)
    gson.toJson(RExpenses(expenses.getId, expenses.getUserId, expenses.getAmount, expenses.getNote, expenses.getCategoryId, expenses.getCreatedDate))
  }

  override def findBy(id: String): String = {
    val expenses = repo.findBy(id)
    gson.toJson(RExpenses(expenses.getId, expenses.getUserId, expenses.getAmount, expenses.getNote, expenses.getCategoryId, expenses.getCreatedDate))
  }

  override def delete(id: String): Boolean = {
    repo.delete(id)
  }
}
