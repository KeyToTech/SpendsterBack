package domain.repositories.base

import java.util

trait BaseGetAllRepository[T] {
  def getAll: util.List[T]
}
