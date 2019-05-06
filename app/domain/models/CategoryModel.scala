package domain.models

import data.entity.Category
import domain.models.base._

trait CategoryModel
  extends BaseGetAllModel
    with BaseFindByModel
    with BaseUpdateModel[Category]
    with BaseSaveModel[Category]
    with BaseDeleteModel{

}
