package domain.models

import data.entity.Expenses
import domain.models.base._

trait ExpensesModel
  extends BaseGetAllModel
    with BaseFindByModel
    with BaseUpdateModel[Expenses]
    with BaseSaveModel[Expenses]
    with BaseDeleteModel{

}
