package data.store

import data.{MorphiaDAO, StoreProvider}
import data.entity.User
import javax.inject.Inject
import org.bson.types.ObjectId

/**
  * Mongo UserStore
  *
  * @author Bohdan Ustiak (bogdan.ustyak@gmail.com)
  */
class MUserStore @Inject()(private val storeProvider: StoreProvider) extends UserStore {

  /**
    * Finds plain User by it's id
    *
    * @param userId - id of User
    * @return user entity
    */
  private def find(userId: String): User = {
    val objectId = new ObjectId(userId)
    this.storeProvider.get().get(classOf[User], objectId)
  }

  /**
    * Finds user with email and password
    *
    * @param email    User email
    * @param password User password
    * @return User if found
    */
  override def find(email: String, password: String): User = {
    val query = this.storeProvider.get().find(classOf[User])
    query.and(
      query.criteria(s"${
        User.EMAIL
      }")
        .equal(email),
      query.criteria(s"${
        User.PASSWORD
      }")
        .equal(password)
    )
    query.get()
  }

  /**
    * Creates a new user
    *
    * @param user To save
    * @return User saved in store
    */
  override def save(user: User): User = {
    val entityId = new MorphiaDAO[User]().save(this.storeProvider.get(), user).getId
    find(entityId.toString)
  }
}
