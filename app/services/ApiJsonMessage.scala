package services

class ApiJsonMessage {

  def create(message: String): String = {
    "{\"message\": \"" + message + "\"}"
  }
}
