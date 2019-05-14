package domain.repositories.simple

import data.entity.User
import data.store.UserStore
import domain.repositories.UserRepository
import javax.inject.Inject
import services.auth.BearerTokenGenerator

/**
  * SimpleUserRepository
  *
  * @author Bohdan Ustiak (bogdan.ustyak@gmail.com)
  */
class SimpleUserRepository @Inject()(private val store: UserStore, private val tokenGenerator: BearerTokenGenerator) extends UserRepository {

  override def login(email: String, password: String): User = {
    val user = this.store.find(email, password)
    if(user != null) {
      user.updateToken(tokenGenerator.generateSHAToken(email))
      this.store.update(user)
    } else {
      null
    }
  }

  override def signUp(username: String, email: String, password: String): User = {
    if (this.login(email, password) == null) {
      this.store.save(new User(username, email, password, tokenGenerator.generateSHAToken(email)))
    } else {
      null
    }
  }
}
