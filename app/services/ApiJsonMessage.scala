package services

class ApiJsonMessage {
  def success(isSuccess: Boolean): String = {
    "{\"success\": \"" + isSuccess.toString + "\"}"
  }

  def error(message: String): String = {
    "{\"error\": \"" + message.replace("\"", "'") + "\"}"
  }

  def token(token: String): String = {
    "{\"token\": " + token + "}"
  }
}
