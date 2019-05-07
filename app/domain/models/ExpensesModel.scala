package domain.models

import java.util.Date

import domain.entity.Expenses
import domain.models.base._

trait ExpensesModel
  extends BaseFindByModel
    with BaseUpdateModel[Expenses]
    with BaseSaveModel[Expenses]
    with BaseDeleteModel{

  def getByRange(start: Date, end: Date): String
}
