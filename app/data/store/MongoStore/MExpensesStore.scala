package data.store.MongoStore

import java.util
import java.util.Date

import data.{MorphiaDAO, StoreProvider}
import data.entity.Expenses
import data.store.ExpensesStore
import javax.inject.Inject
import org.bson.types.ObjectId

/**
  * Mongo ExpensesStore
  */
class MExpensesStore @Inject()(private val storeProvider: StoreProvider) extends ExpensesStore{

  /**
    * Finds Expenses by id
    *
    * @param expensesId Expenses id
    * @return Expenses if found
    */
  override def find(expensesId: String): Expenses = {
    val objectId = new ObjectId(expensesId)
    this.storeProvider.get().get(classOf[Expenses], objectId)
  }

  /**
    * Finds Expenses with userId within Date range
    *
    * @param userId    User id
    * @param startDate Date of start
    * @param endDate   Date of end
    * @return List of Expenses
    */
  override def findRange(userId: String, startDate: Date, endDate: Date): util.List[Expenses] = {
    val query = this.storeProvider.get().find(classOf[Expenses])
    query.and(
      query.criteria(
        "userId"
      ).equal(userId),
      query.criteria(
        "createdDate"
      ).greaterThanOrEq(startDate),
      query.criteria(
        "createdDate"
      ).lessThanOrEq(endDate)
    )
    query.asList()
  }

  /**
    * Updates existing Expenses
    *
    * @param expenses Expenses to update
    * @return Updated Expenses
    */
  override def update(expenses: Expenses): Expenses = {
    val query = this.storeProvider.get().find(classOf[Expenses])
      .field("id").equal(new ObjectId(expenses.getId))
    val operations = this.storeProvider.get().createUpdateOperations(classOf[Expenses])
      .set("amount", expenses.getAmount)
      .set("note", expenses.getNote)
      .set("categoryId", expenses.getCategoryId)
      .set("createdDate", expenses.getCreatedDate)
    this.storeProvider.get().update(query, operations)
    this.find(expenses.getId)
  }

  /**
    * Creates a new Expenses
    *
    * @param expenses Expenses to create
    * @return Expenses saved in store
    */
  override def save(expenses: Expenses): Expenses = {
    val entityId = new MorphiaDAO[Expenses].save(this.storeProvider.get(), expenses).getId
    find(entityId.toString)
  }

  /**
    * Deletes existing Expenses
    *
    * @param expensesId Expenses id to delete
    * @return Success of operation
    */
  override def delete(expensesId: String): Boolean = {
    val query = this.storeProvider.get().find(classOf[Expenses])
    query.and(
      query.criteria(
        "id"
      ).equal(new ObjectId(expensesId))
    )
    this.storeProvider.get().delete(query)
    true
  }
}
