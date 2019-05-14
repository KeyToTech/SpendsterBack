package domain.models.impl

import java.util.Date

import com.google.gson.Gson
import data.entity.Expenses
import domain.models.ExpensesModel
import domain.repositories.ExpensesRepository
import javax.inject.Inject

class SimpleExpensesModel @Inject()(repo: ExpensesRepository,
                                    gson: Gson)
  extends ExpensesModel{

  override def getByRange(start: Date, end: Date): String = {
    gson.toJson(repo.getByRange(start, end))
  }

  override def update(obj: Expenses): String = {
    gson.toJson(repo.update(obj))
  }

  override def save(obj: Expenses): String = {
    gson.toJson(repo.save(obj))
  }

  override def findBy(id: String): String = {
    gson.toJson(repo.findBy(id))
  }

  override def delete(id: String): Boolean = {
    repo.delete(id)
  }
}
