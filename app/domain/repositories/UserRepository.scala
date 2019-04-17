package domain.repositories

import domain.entity.User

class UserRepository {
  def login(username: String, password: String): User ={
    //TODO: change this to get actual user
    val user = new User()
    user.setUsername(username)
    user.setPassword(password)
    return user
  }

  def signUp(username: String, email: String, password: String): User = {
    //TODO: change this to save user in db and get actual user
    val user = new User()
    user.setUsername(username)
    user.setEmail(email)
    user.setPassword(password)
    return user
  }
}
