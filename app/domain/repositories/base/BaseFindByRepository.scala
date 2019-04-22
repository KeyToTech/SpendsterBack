package domain.repositories.base

trait BaseFindByRepository[T] {

  def findBy(id: String): T
}
