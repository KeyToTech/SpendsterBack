package domain.models

trait UserModel {

  def login(email: String, password: String): String
  def signUp(username: String, email: String, password: String): String
}
