package controllers

import java.text.{ParseException, SimpleDateFormat}
import java.util.Date

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
        InternalServerError(message.create(e.getLocalizedMessage.replace("\"", "'")))
    }
  }

  def findBy(id: String) = Action {
    try{
      Ok(model.findBy(id))
    }
    catch {
      case e: Exception =>
        InternalServerError(message.create(e.getLocalizedMessage.replace("\"", "'")))
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
                  BadRequest(message.create(e.getLocalizedMessage.replace("\"", "'") +
                    " Date format: dd/mm/yyyy hh:mm"))
                case e: Exception =>
                  InternalServerError(message.create(e.getLocalizedMessage.replace("\"", "'")))
              }
            }.getOrElse{
              BadRequest(message.create("Expecting CreatedDate"))
            }
          }.getOrElse{
            BadRequest(message.create("Expecting categoryId"))
          }
        }.getOrElse{
          BadRequest(message.create("Expecting amount"))
        }
      }.getOrElse{
        BadRequest(message.create("Expecting id"))
      }
    }.getOrElse{
      BadRequest(message.create("Expecting category data"))
    }
  }

  def save = Action{implicit request =>
    request.body.asJson.map {json =>
      (json \ "amount").asOpt[Double].map{amount =>
        (json \ "category").asOpt[String].map{categoryId =>
          try{
            val obj = new Expenses(new String, amount, categoryId, new Date)

            Created(model.update(obj))
          }
          catch {
            case e: Exception =>
              InternalServerError(message.create(e.getLocalizedMessage.replace("\"", "'")))
          }
        }.getOrElse{
          BadRequest(message.create("Expecting category id"))
        }
      }.getOrElse{
        BadRequest(message.create("Expecting amount"))
      }
    }.getOrElse{
      BadRequest(message.create("Expecting category data"))
    }
  }

  def delete(id: String) = Action {
    try {
      Ok(model.delete(id))
    }
    catch {
      case e: Exception =>
        InternalServerError(message.create(e.getLocalizedMessage.replace("\"", "'")))
    }
  }
}

