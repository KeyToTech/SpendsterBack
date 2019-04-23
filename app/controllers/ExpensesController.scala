package controllers

import java.util.Date

import com.google.gson.Gson
import domain.entity.{Category, Expenses}
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}


class ExpensesController @Inject()(cc: ControllerComponents,
                                   //TODO: add repo here and uncomment returns in methods https://trello.com/c/gJ2Nzjgc/104-add-expenses-repository
                                   gson: Gson)
  extends AbstractController(cc){

  def getAll = Action {
    //Ok(repo.getAll())
    throw new UnsupportedOperationException()
    Ok("")
  }

  def getOne(id: String) = Action {
    //Ok(repo.getOne(id))
    throw new UnsupportedOperationException()
    Ok("")
  }

  def update = Action {implicit request =>
    request.body.asJson.map {json =>
      (json \ "id").asOpt[Int].map{id =>
        (json \ "amount").asOpt[Double].map{amount =>
          (json \ "category").asOpt[Int].map{categoryId =>
            (json \ "CreatedDate").asOpt[Date].map{date =>
              //Ok(repo.update(id, amount, categoryId, date))
              throw new UnsupportedOperationException()
              Ok("")
            }.getOrElse{
              BadRequest("Expecting date")
            }
          }.getOrElse{
            BadRequest("Expecting category id")
          }
        }.getOrElse{
          BadRequest("Expecting amount")
        }
      }.getOrElse{
        BadRequest("Expecting id")
      }
    }.getOrElse{
      BadRequest("Expecting category data")
    }
  }

  def save = Action{implicit request =>
    request.body.asJson.map {json =>
      (json \ "amount").asOpt[Double].map{amount =>
        (json \ "category").asOpt[Int].map{categoryId =>
          (json \ "CreatedDate").asOpt[Date].map{date =>
            //Ok(repo.update(amount, categoryId, date))
            throw new UnsupportedOperationException()
            Ok("")
          }.getOrElse{
            BadRequest("Expecting date")
          }
        }.getOrElse{
          BadRequest("Expecting category id")
        }
      }.getOrElse{
        BadRequest("Expecting amount")
      }
    }.getOrElse{
      BadRequest("Expecting category data")
    }
  }

  def delete(id: String) = Action{
    //Ok(repo.delete(id))
    throw new UnsupportedOperationException()
    Ok("")
  }
}

