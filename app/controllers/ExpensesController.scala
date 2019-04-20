package controllers

import java.text.{ParseException, SimpleDateFormat}

import domain.entity.Expenses
import domain.models.ExpensesModel
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import services.ApiJsonMessage


class ExpensesController @Inject()(cc: ControllerComponents,
                                   model: ExpensesModel)
  extends AbstractController(cc){

  private val message = new ApiJsonMessage()

  def getAll = Action {
    try{
      Ok(model.getAll)
    }
    catch {
      case e: Exception =>
        InternalServerError(message.create(e.getLocalizedMessage.replace("\"", "'")))
    }
  }

  def getOne(id: String) = Action {
    try{
      Ok(model.getOne(id))
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
                val obj = new Expenses
                obj.setId(id)
                obj.setAmount(amount)
                obj.setCategoryId(categoryId)
                obj.setCreatedDate(new SimpleDateFormat("dd/M/yyyy hh:mm").parse(dateString))

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
            val obj = new Expenses
            obj.setAmount(amount)
            obj.setCategoryId(categoryId)

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

