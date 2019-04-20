package domain.models.base

trait BaseGetOneModel {

  def getOne(id: String): String
}
