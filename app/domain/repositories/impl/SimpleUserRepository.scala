package domain.repositories.impl

import domain.entity.User
import domain.repositories.UserRepository

class SimpleUserRepository extends UserRepository{
  
  def login(username: String, password: String): User ={
    //TODO: change this to get actual user https://trello.com/c/YH0aogAI/86-create-db-entities
    val user = new User()
    user.setUsername(username)
    user.setPassword(password)
    return user
  }

  def signUp(username: String, email: String, password: String): User = {
    //TODO: change this to save user in db and get actual user https://trello.com/c/YH0aogAI/86-create-db-entities
    val user = new User()
    user.setUsername(username)
    user.setEmail(email)
    user.setPassword(password)
    return user
  }
}
