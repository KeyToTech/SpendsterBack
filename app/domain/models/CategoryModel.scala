package domain.models

import data.entity.Category

trait CategoryModel
  extends Model[Category]{

  def getAll(userId: String): String
}
