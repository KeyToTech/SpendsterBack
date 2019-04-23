package domain.models.base

trait BaseDeleteModel {
  def delete(id: String): String
}
