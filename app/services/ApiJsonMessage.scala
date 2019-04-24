package services

class ApiJsonMessage {

  def create(message: String): String = {
    "{\"error\": \"" + message.replace("\"", "'") + "\"}"
  }
}
