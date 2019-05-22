package domain.repositories.base

import java.util

trait BaseGetAllRepository[T] {
  def getAll(userId: String): util.List[T]
}
