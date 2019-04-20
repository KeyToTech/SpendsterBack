package domain.repositories.base

trait BaseGetOneRepository[T] {

  def getOne(id: String): T
}
