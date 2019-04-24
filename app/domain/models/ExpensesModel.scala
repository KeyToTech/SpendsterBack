package domain.models

import domain.entity.Expenses
import domain.models.base._

trait ExpensesModel
  extends BaseGetAllModel
    with BaseFindByModel
    with BaseUpdateModel[Expenses]
    with BaseSaveModel[Expenses]
    with BaseDeleteModel{

}
