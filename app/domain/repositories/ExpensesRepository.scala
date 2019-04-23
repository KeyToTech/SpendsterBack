package domain.repositories

import domain.entity.Expenses
import domain.repositories.base._

trait ExpensesRepository
  extends BaseGetAllRepository[Expenses]
    with BaseFindByRepository[Expenses]
    with BaseUpdateRepository[Expenses]
    with BaseSaveRepository[Expenses]
    with BaseDeleteRepository{

}
