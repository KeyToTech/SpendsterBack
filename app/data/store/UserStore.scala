package data.store

import data.entity.User

/**
  * UserStore
  *
  * @author Bohdan Ustiak (bogdan.ustyak@gmail.com)
  */
trait UserStore {

  /**
    * Finds user with email and password
    *
    * @param email    User email
    * @param password User password
    * @return User if found
    */
  def find(email: String, password: String): User

  /**
    * Creates a new user
    *
    * @param user To save
    * @return User saved in store
    * @throws IllegalArgumentException if user is null
    */
  def save(user: User): User
}
