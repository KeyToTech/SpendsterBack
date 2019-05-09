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
              BadRequest(message.error(e.getLocalizedMessage +
                " Date format: dd/mm/yyyy hh:mm"))
            case e: Exception =>
              InternalServerError(message.error(e.getLocalizedMessage))
          }
        }.getOrElse{
          BadRequest(message.error("Expecting end date"))
        }
      }.getOrElse{
        BadRequest(message.error("Expecting start date"))
      }
    }.getOrElse{
      BadRequest(message.error("Expecting start and end dates"))
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
    request.body.asJson.map { json =>
      (json \ "id").asOpt[String].map { id =>
        (json \ "userId").asOpt[String].map { userId =>
          (json \ "amount").asOpt[Double].map { amount =>
            (json \ "note").asOpt[String].map { note =>
              (json \ "categoryId").asOpt[String].map { categoryId =>
                (json \ "сreatedDate").asOpt[String].map { dateString =>
                  try {
                    val obj = new Expenses(id, userId, amount, note, categoryId, new SimpleDateFormat("dd/M/yyyy hh:mm").parse(dateString))
                    Ok(model.update(obj))
                  }
                  catch {
                    case e: ParseException =>
                      BadRequest(message.error(e.getLocalizedMessage +
                        " Date format: dd/mm/yyyy hh:mm"))
                    case e: Exception =>
                      InternalServerError(message.error(e.getLocalizedMessage))
                  }
                }.getOrElse {
                  BadRequest(message.error("Expecting CreatedDate"))
                }
              }.getOrElse {
                BadRequest(message.error("Expecting categoryId"))
              }
            }.getOrElse {
              BadRequest(message.error("Expecting note"))
            }
          }.getOrElse {
            BadRequest(message.error("Expecting amount"))
          }
        }.getOrElse {
          BadRequest(message.error("Expecting userId"))
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
      (json \ "userId").asOpt[String].map { userId =>
        (json \ "amount").asOpt[Double].map { amount =>
          (json \ "note").asOpt[String].map { note =>
            (json \ "categoryId").asOpt[String].map { categoryId =>
              try {
                val obj = new Expenses(userId, amount, note, categoryId)
                Created(model.update(obj))
              }
              catch {
                case e: Exception =>
                  InternalServerError(message.error(e.getLocalizedMessage))
              }
            }.getOrElse {
              BadRequest(message.error("Expecting category id"))
            }
          }.getOrElse {
            BadRequest(message.error("Expecting note"))
          }
        }.getOrElse {
          BadRequest(message.error("Expecting amount"))
        }
      }.getOrElse {
        BadRequest(message.error("Expecting userId"))
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

