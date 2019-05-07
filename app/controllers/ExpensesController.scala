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

  def getByRange = Action {implicit request =>
    request.body.asJson.map {json =>
      (json \ "start").asOpt[String].map{start =>
        (json \ "end").asOpt[String].map{end =>
          try{
            Ok(model.getByRange(new SimpleDateFormat("dd/M/yyyy hh:mm").parse(start), new SimpleDateFormat("dd/M/yyyy hh:mm").parse(end)))
          }
          catch {
            case e: ParseException =>
              BadRequest(message.create(e.getLocalizedMessage +
                " Date format: dd/mm/yyyy hh:mm"))
            case e: Exception =>
              InternalServerError(message.create(e.getLocalizedMessage))
          }
        }.getOrElse{
          BadRequest(message.create("Expecting end date"))
        }
      }.getOrElse{
        BadRequest(message.create("Expecting start date"))
      }
    }.getOrElse{
      BadRequest(message.create("Expecting start and end dates"))
    }
  }

  def findBy(id: String) = Action {
    try{
      Ok(model.findBy(id))
    }
    catch {
      case e: Exception =>
        InternalServerError(message.create(e.getLocalizedMessage))
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
                  BadRequest(message.create(e.getLocalizedMessage +
                    " Date format: dd/mm/yyyy hh:mm"))
                case e: Exception =>
                  InternalServerError(message.create(e.getLocalizedMessage))
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
        (json \ "categoryId").asOpt[String].map{categoryId =>
          try{
            val obj = new Expenses(amount, categoryId)
            Created(model.update(obj))
          }
          catch {
            case e: Exception =>
              InternalServerError(message.create(e.getLocalizedMessage))
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
      Ok(model.delete(id).toString)
    }
    catch {
      case e: Exception =>
        InternalServerError(message.create(e.getLocalizedMessage))
    }
  }
}

