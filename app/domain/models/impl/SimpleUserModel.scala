package domain.models.impl

import com.google.gson.Gson
import domain.models.UserModel
import domain.repositories.UserRepository
import javax.inject.Inject

class SimpleUserModel @Inject()(repo: UserRepository,
                                gson: Gson)
  extends UserModel{

  override def login(username: String, password: String): String = {
    gson.toJson(repo.login(username, password))
  }

  override def signUp(username: String, email: String, password: String): String = {
    gson.toJson(repo.signUp(username, email, password))
  }
}
