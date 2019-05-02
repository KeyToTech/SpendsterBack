package controllers

import java.text.{ParseException, SimpleDateFormat}

import domain.entity.Expenses
import domain.models.ExpensesModel
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import services.ApiJsonMessage


class ExpensesController @Inject()(cc: ControllerComponents,
                                   model: ExpensesModel,
                                   message: ApiJsonMessage)
  extends AbstractController(cc){

  def getAll = Action {
    try{
      Ok(model.getAll)
    }
    catch {
      case e: Exception =>
        InternalServerError(message.error(e.getLocalizedMessage))
    }
  }

  def findBy(id: String) = Action {
    try{
      Ok(model.findBy(id))
    }
    catch {
      case e: Exception =>
        InternalServerError(message.error(e.getLocalizedMessage))
    }
  }

  def update = Action {implicit request =>
    request.body.asJson.map {json =>
      (json \ "id").asOpt[String].map{id =>
        (json \ "amount").asOpt[Double].map{amount =>
          (json \ "categoryId").asOpt[String].map{categoryId =>
            (json \ "CreatedDate").asOpt[String].map{dateString =>
              try{
                val obj = new Expenses(id, amount, categoryId, new SimpleDateFormat("dd/M/yyyy hh:mm").parse(dateString))
                Ok(model.update(obj))
              }
              catch {
                case e: ParseException =>
                  BadRequest(message.error(e.getLocalizedMessage +
                    " Date format: dd/mm/yyyy hh:mm"))
                case e: Exception =>
                  InternalServerError(message.error(e.getLocalizedMessage))
              }
            }.getOrElse{
              BadRequest(message.error("Expecting CreatedDate"))
            }
          }.getOrElse{
            BadRequest(message.error("Expecting categoryId"))
          }
        }.getOrElse{
          BadRequest(message.error("Expecting amount"))
        }
      }.getOrElse{
        BadRequest(message.error("Expecting id"))
      }
    }.getOrElse{
      BadRequest(message.error("Expecting category data"))
    }
  }

  def save = Action{implicit request =>
    request.body.asJson.map {json =>
      (json \ "amount").asOpt[Double].map{amount =>
        (json \ "categoryId").asOpt[String].map{categoryId =>
          try{
            val obj = new Expenses(amount, categoryId)
            Created(model.update(obj))
          }
          catch {
            case e: Exception =>
              InternalServerError(message.error(e.getLocalizedMessage))
          }
        }.getOrElse{
          BadRequest(message.error("Expecting category id"))
        }
      }.getOrElse{
        BadRequest(message.error("Expecting amount"))
      }
    }.getOrElse{
      BadRequest(message.error("Expecting category data"))
    }
  }

  def delete(id: String) = Action {
    try {
      Ok(message.success(model.delete(id)))
    }
    catch {
      case e: Exception =>
        InternalServerError(message.error(e.getLocalizedMessage))
    }
  }
}

