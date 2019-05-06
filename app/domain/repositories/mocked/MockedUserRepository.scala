package domain.repositories.mocked

import data.entity.User
import domain.repositories.UserRepository

class MockedUserRepository extends UserRepository{
  override def login(email: String, password: String): User = {
    new User("mockedUsername", email, password)
  }

  override def signUp(username: String, email: String, password: String): User = {
    new User(username, email, password)
  }
}
