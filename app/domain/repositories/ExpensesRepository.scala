package domain.repositories

import data.entity.Expenses
import domain.repositories.base._

trait ExpensesRepository
  extends BaseGetAllRepository[Expenses]
    with BaseFindByRepository[Expenses]
    with BaseUpdateRepository[Expenses]
    with BaseSaveRepository[Expenses]
    with BaseDeleteRepository{

}
