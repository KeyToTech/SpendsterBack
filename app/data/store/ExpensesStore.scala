package data.store

import java.util
import java.util.Date

import data.entity.Expenses

/**
  * ExpensesStore
  */
trait ExpensesStore {

  /**
    * Finds Expenses by id
    *
    * @param expensesId Expenses id
    * @return           Expenses if found
    */
  def find(expensesId: String): Expenses

  /**
    * Finds Expenses with userId within Date range
    *
    * @param userId     User id
    * @param startDate  Date of start
    * @param endDate    Date of end
    * @return           List of Expenses
    */
  def findRange(userId: String, startDate: Date, endDate: Date): util.List[Expenses]

  /**
    * Updates existing Expenses
    *
    * @param expenses  Expenses to update
    * @return          Updated Expenses
    */
  def update(expenses: Expenses): Expenses

  /**
    * Creates a new Expenses
    *
    * @param expenses  Expenses to create
    * @return          Expenses saved in store
    */
  def save(expenses: Expenses): Expenses

  /**
    * Deletes existing Expenses
    *
    * @param expensesId Expenses id to delete
    * @return           Success of operation
    */
  def delete(expensesId: String): Boolean
}
