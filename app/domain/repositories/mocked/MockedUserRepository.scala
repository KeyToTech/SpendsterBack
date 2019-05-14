package domain.repositories.mocked

import data.entity.User
import domain.repositories.UserRepository
import javax.inject.Inject
import services.auth.BearerTokenGenerator

class MockedUserRepository @Inject()(tokenGenerator: BearerTokenGenerator) extends UserRepository{
  private val TOKEN_EXPIRES_TIME: Long = 7 * 86400000

  override def login(email: String, password: String): User = {
    new User("mockedUsername", email, password, tokenGenerator.generateSHAToken(email), TOKEN_EXPIRES_TIME)
  }

  override def signUp(username: String, email: String, password: String): User = {
    new User(username, email, password, tokenGenerator.generateSHAToken(email), TOKEN_EXPIRES_TIME)
  }
}
