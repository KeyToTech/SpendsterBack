package domain.repositories

import domain.entity.User

trait UserRepository {

  def login(username: String, password: String): User
  def signUp(username: String, email: String, password: String): User
}
