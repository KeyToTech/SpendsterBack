package domain.repositories

class UserRepository {
  def signUp(username: String, email: String, password: String): String = {
    username + " " + email + " " + password
  }
}
