package domain.repositories.base

trait BaseSaveRepository[T] {
  def save(obj: T): T
}
