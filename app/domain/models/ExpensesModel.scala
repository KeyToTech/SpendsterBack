package domain.models

import java.util.Date
import data.entity.Expenses
import domain.models.base._

trait ExpensesModel
  extends BaseFindByModel
    with BaseUpdateModel[Expenses]
    with BaseSaveModel[Expenses]
    with BaseDeleteModel{

  def getByRange(userId: String, start: Date, end: Date): String
}
