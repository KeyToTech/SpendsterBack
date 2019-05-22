package domain.models.simple

import com.google.gson.Gson
import domain.models.UserModel
import domain.models.responseEntities.RUser
import domain.repositories.UserRepository
import javax.inject.Inject

class SimpleUserModel @Inject()(repo: UserRepository,
                                gson: Gson)
  extends UserModel{

  override def login(email: String, password: String): String = {
    val user = repo.login(email, password)
    if (user == null){
      throw new IllegalArgumentException("There is no such user")
    }
    gson.toJson(new RUser(user))
  }

  override def signUp(username: String, email: String, password: String): String = {
    val user = repo.signUp(username, email, password)
    if (user == null){
      throw new IllegalArgumentException("User already exists")
    }
    gson.toJson(new RUser(user))
  }
}
