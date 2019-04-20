package domain.repositories.base

trait BaseDeleteRepository[T] {

  def delete(id: String): T
}
