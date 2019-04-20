package domain.models

import domain.entity.Category
import domain.models.base._

trait CategoryModel
  extends BaseGetAllModel
    with BaseGetOneModel
    with BaseUpdateModel[Category]
    with BaseSaveModel[Category]
    with BaseDeleteModel{

}
