package domain.repositories

import data.entity.Expenses
import java.util
import java.util.Date
import domain.repositories.base._

trait ExpensesRepository
  extends BaseFindByRepository[Expenses]
    with BaseUpdateRepository[Expenses]
    with BaseSaveRepository[Expenses]
    with BaseDeleteRepository{

  def getByRange(userId: String, start: Date, end: Date, offset: Int, limit: Int): util.List[Expenses]
}
