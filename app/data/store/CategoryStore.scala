package data.store

import data.entity.Category
import java.util

/**
  * CategoryStore
  */
trait CategoryStore {

  /**
    * Finds Category by id
    *
    * @param categoryId Category id
    * @return           Category if found
    */
  def find(categoryId: String): Category

  /**
    * Finds Categories with userId
    *
    * @param userId     User id
    * @return           List of Categories
    */
  def getAll(userId: String): util.List[Category]

  /**
    * Updates existing Category
    *
    * @param category Category to update
    * @return         Updated Category
    */
  def update(category: Category): Category

  /**
    * Creates new Category
    *
    * @param category Category to save
    * @return         Category saved in store
    */
  def save(category: Category): Category

  /**
    * Deletes existing Category
    *
    * @param categoryId Category id to delete
    * @return           Success of operation
    */
  def delete(categoryId: String): Boolean
}
