package domain.models.simple

import java.util.Date
import java.util

import com.google.gson.Gson
import data.entity.Expenses
import domain.models.ExpensesModel
import domain.models.responseEntities.{RCategory, RExpenses}
import domain.repositories.{CategoryRepository, ExpensesRepository}
import javax.inject.Inject

class SimpleExpensesModel @Inject()(repo: ExpensesRepository,
                                    categoryRepo: CategoryRepository,
                                    gson: Gson)
  extends ExpensesModel{

  private def getCategory(id: String): RCategory = {
    try {
      val category = categoryRepo.findBy(id)
      new RCategory(category)
    }
    catch {
      case _: Exception =>
        throw new IllegalArgumentException("There is no such category")
    }
  }

  override def getByRange(userId: String, start: Date, end: Date, offset: Int, limit: Int): String = {
    val rExpensesList = new util.ArrayList[RExpenses]
    repo.getByRange(userId, start, end, offset, limit)
      .forEach(expenses => {
        rExpensesList.add(new RExpenses(expenses, getCategory(expenses.getCategoryId)))
      })
    gson.toJson(rExpensesList)
  }

  override def update(obj: Expenses): String = {
    val category = getCategory(obj.getCategoryId)
    val expenses = repo.update(obj)
    gson.toJson(new RExpenses(expenses, category))
  }

  override def save(obj: Expenses): String = {
    val category = getCategory(obj.getCategoryId)
    val expenses = repo.save(obj)
    gson.toJson(new RExpenses(expenses, category))
  }

  override def findBy(id: String): String = {
    val expenses = repo.findBy(id)
    gson.toJson(new RExpenses(expenses, getCategory(expenses.getCategoryId)))
  }

  override def delete(id: String): Boolean = {
    repo.delete(id)
  }
}
