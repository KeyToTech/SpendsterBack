package domain.repositories

import domain.requestEntities.RUser


class UserRepository {
  def signUp(user: RUser): String = {
    user.username
  }
}
