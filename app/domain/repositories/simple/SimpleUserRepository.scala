package domain.repositories.simple

import data.entity.User
import data.store.UserStore
import domain.repositories.UserRepository
import javax.inject.Inject

/**
  * SimpleUserRepository
  *
  * @author Bohdan Ustiak (bogdan.ustyak@gmail.com)
  */
class SimpleUserRepository @Inject()(private val store: UserStore) extends UserRepository {

  override def login(email: String, password: String): User = {
    this.store.find(email, password)
  }

  override def signUp(username: String, email: String, password: String): User = {
    if (this.login(email, password) == null) {
      this.store.save(new User(username, email, password))
    } else {
      throw new IllegalArgumentException("this user already exist")
    }
  }
}
