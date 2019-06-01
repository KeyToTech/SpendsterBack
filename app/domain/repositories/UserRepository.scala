package domain.repositories

import data.entity.User

trait UserRepository {

  def login(email: String, password: String): User

  /**
    * Creates a user
    *
    * @param username
    * @param email
    * @param password
    * @return Created user
    * @throws IllegalArgumentException if user already exists
    */
  def signUp(username: String, email: String, password: String): User
}
