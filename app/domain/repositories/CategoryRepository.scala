package domain.repositories

import base._
import domain.entity.Category

trait CategoryRepository
  extends BaseGetAllRepository[Category]
    with BaseGetOneRepository[Category]
    with BaseUpdateRepository[Category]
    with BaseSaveRepository[Category]
    with BaseDeleteRepository[Category]{

}
