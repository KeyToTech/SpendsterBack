package domain.repositories

import domain.entity.Expenses
import domain.repositories.base._

trait ExpensesRepository
  extends BaseGetAllRepository[Expenses]
    with BaseGetOneRepository[Expenses]
    with BaseUpdateRepository[Expenses]
    with BaseSaveRepository[Expenses]
    with BaseDeleteRepository[Expenses]{

}
