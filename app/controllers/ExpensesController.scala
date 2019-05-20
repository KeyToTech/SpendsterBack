package controllers

import java.util.Date

import data.entity.Expenses
import domain.models.ExpensesModel
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import services.ApiJsonMessage
import services.auth.AuthAction


class ExpensesController @Inject()(cc: ControllerComponents,
                                   authAction: AuthAction,
                                   model: ExpensesModel,
                                   message: ApiJsonMessage)
  extends AbstractController(cc){

  def getByRange(userId: String, startDate: String, endDate: String) = authAction {
    try {
      Ok(model.getByRange(userId, new Date(startDate.toLong), new Date(endDate.toLong)))
    }
    catch {
      case e: Exception =>
        InternalServerError(message.error(e.getLocalizedMessage))
    }
  }

  def findBy(id: String) = authAction {
    try{
      Ok(model.findBy(id))
    }
    catch {
      case e: Exception =>
        InternalServerError(message.error(e.getLocalizedMessage))
    }
  }

  def update = authAction {implicit request =>
    request.body.asJson.map { json =>
      (json \ "id").asOpt[String].map { id =>
        (json \ "userId").asOpt[String].map { userId =>
          (json \ "amount").asOpt[Double].map { amount =>
            (json \ "note").asOpt[String].map { note =>
              (json \ "categoryId").asOpt[String].map { categoryId =>
                (json \ "createdDate").asOpt[Long].map { dateTimestamp =>
                  try {
                    val obj = new Expenses(id, userId, amount, note, categoryId, new Date(dateTimestamp))
                    Ok(model.update(obj))
                  }
                  catch {
                    case e: Exception =>
                      InternalServerError(message.error(e.getLocalizedMessage))
                  }
                }.getOrElse {
                  BadRequest(message.error("Expecting createdDate"))
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
      BadRequest(message.error("Expecting expenses data"))
    }
  }

  def save = authAction{implicit request =>
    request.body.asJson.map {json =>
      (json \ "userId").asOpt[String].map { userId =>
        (json \ "amount").asOpt[Double].map { amount =>
          (json \ "note").asOpt[String].map { note =>
            (json \ "categoryId").asOpt[String].map { categoryId =>
              try {
                val obj = new Expenses(userId, amount, note, categoryId)
                Created(model.save(obj))
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
      BadRequest(message.error("Expecting expenses data"))
    }
  }

  def delete(id: String) = authAction {
    try {
      Ok(message.success(model.delete(id)))
    }
    catch {
      case e: Exception =>
        InternalServerError(message.error(e.getLocalizedMessage))
    }
  }
}

