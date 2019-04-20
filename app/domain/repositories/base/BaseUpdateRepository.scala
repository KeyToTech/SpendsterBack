package domain.repositories.base

trait BaseUpdateRepository[T] {

  def update(obj: T): T
}
