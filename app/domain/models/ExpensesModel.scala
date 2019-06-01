package domain.models

import java.util.Date

import data.entity.Expenses

trait ExpensesModel extends Model[Expenses]{

  def getByRange(userId: String, start: Date, end: Date, offset: Int, limit: Int): String
}
