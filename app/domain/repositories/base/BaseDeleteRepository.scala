package domain.repositories.base

trait BaseDeleteRepository[T] {

  def delete(obj: T): T
}
