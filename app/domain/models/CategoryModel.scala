package domain.models

import data.entity.Category
import domain.models.base._

trait CategoryModel
  extends Model[Category]{

  def getAll(userId: String): String
}
