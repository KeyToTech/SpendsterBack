package data.store.MongoStore

import java.util

import data.{MorphiaDAO, StoreProvider}
import data.entity.Category
import data.store.CategoryStore
import javax.inject.Inject
import org.bson.types.ObjectId

/**
  * Mongo CategoryStore
  */
class MCategoryStore @Inject()(private val storeProvider: StoreProvider) extends CategoryStore{

  /**
    * Finds Category by id
    *
    * @param categoryId Category id
    * @return Category if found
    */
  override def find(categoryId: String): Category = {
    val objectId = new ObjectId(categoryId)
    this.storeProvider.get().get(classOf[Category], objectId)
  }

  /**
    * Finds Categories with userId
    *
    * @param userId User id
    * @return List of Categories
    */
  override def getAll(userId: String): util.List[Category] = {
    val query = this.storeProvider.get().find(classOf[Category])
    query.and(
      query.criteria(
        "userId"
      ).equal(userId)
    )
    query.asList()
  }

  /**
    * Updates existing Category
    *
    * @param category Category to update
    * @return Updated Category
    */
  override def update(category: Category): Category = {
    val query = this.storeProvider.get().find(classOf[Category])
      .field("id").equal(new ObjectId(category.getId))
    val operations = this.storeProvider.get().createUpdateOperations(classOf[Category])
      .set("name", category.getName)
      .set("type", category.getType)
      .set("icon", category.getIcon)
      .set("createdDate", category.getCreatedDate)
    this.storeProvider.get().update(query, operations)
    this.find(category.getId)
  }

  /**
    * Creates new Category
    *
    * @param category Category to save
    * @return Category saved in store
    */
  override def save(category: Category): Category = {
    val entityId = new MorphiaDAO[Category].save(this.storeProvider.get(), category).getId
    find(entityId.toString)
  }

  /**
    * Deletes existing Category
    *
    * @param categoryId Category id to delete
    * @return Success of operation
    */
  override def delete(categoryId: String): Boolean = {
    val query = this.storeProvider.get().find(classOf[Category])
    query.and(
      query.criteria(
        "id"
      ).equal(new ObjectId(categoryId))
    )
    this.storeProvider.get().delete(query)
    true
  }
}
