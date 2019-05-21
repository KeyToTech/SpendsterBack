package domain.models

import java.util.Date
import data.entity.Expenses
import domain.models.base._

trait ExpensesModel extends Model[Expenses]{

  def getByRange(userId: String, start: Date, end: Date): String
}
