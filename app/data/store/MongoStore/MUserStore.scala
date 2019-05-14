package data.store.MongoStore

import data.entity.User
import data.store.UserStore
import data.{MorphiaDAO, StoreProvider}
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
  override def find(userId: String): User = {
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

  /**
    * Updates existing user
    *
    * @param user To update
    * @return
    */
  override def update(user: User): User = {
    val query = this.storeProvider.get().find(classOf[User])
      .field("id").equal(new ObjectId(user.getId))
    val operations = this.storeProvider.get().createUpdateOperations(classOf[User])
      .set("username", user.getUsername)
      .set(User.EMAIL, user.getEmail)
      .set(User.PASSWORD, user.getPassword)
      .set("balance", user.getBalance)
      .set("createdDate", user.getCreatedDate)
      .set("token", user.getToken)
      .set("tokenExpiresDate", user.getTokenExpiresDate)
    this.storeProvider.get().update(query, operations)
    this.find(user.getId)
  }
}
