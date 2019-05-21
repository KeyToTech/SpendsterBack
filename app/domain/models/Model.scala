package domain.models

trait Model[T]{

  def delete(id: String): Boolean
  def findBy(id: String): String
  def save(obj: T): String
  def update(obj: T): String
}
