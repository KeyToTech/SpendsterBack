package domain.models.simple

import com.google.gson.Gson
import domain.models.UserModel
import domain.repositories.UserRepository
import javax.inject.Inject

case class RUser(
               id: String,
               token: String,
               username: String,
               email: String,
               balance: Double
               )

class SimpleUserModel @Inject()(repo: UserRepository,
                                gson: Gson)
  extends UserModel{

  override def login(email: String, password: String): String = {
    val user = repo.login(email, password)
    if (user == null){
      throw new IllegalArgumentException("There is no such user")
    }
    gson.toJson(RUser(user.getId, user.getToken, user.getUsername, user.getEmail, user.getBalance))
  }

  override def signUp(username: String, email: String, password: String): String = {
    val user = repo.signUp(username, email, password)
    if (user == null){
      throw new IllegalArgumentException("User already exists")
    }
    gson.toJson(RUser(user.getId, user.getToken, user.getUsername, user.getEmail, user.getBalance))
  }
}
