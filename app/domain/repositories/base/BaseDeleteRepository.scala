package domain.repositories.base

trait BaseDeleteRepository {

  def delete(id: String): Boolean
}
