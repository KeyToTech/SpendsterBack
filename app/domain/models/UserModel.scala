package domain.models

trait UserModel {

  def login(username: String, password: String): String
  def signUp(username: String, email: String, password: String): String
}
