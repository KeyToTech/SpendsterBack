package controllers

import java.text.{ParseException, SimpleDateFormat}

import domain.entity.Category
import domain.models.CategoryModel
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import services.ApiJsonMessage


class CategoryController @Inject()(cc: ControllerComponents,
                                   model: CategoryModel,
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
        (json \ "name").asOpt[String].map{name =>
          (json \ "type").asOpt[String].map{categoryType =>
            (json \ "сreatedDate").asOpt[String].map{dateString =>
              try{
                val obj = new Category(id, name, categoryType, new SimpleDateFormat("dd/M/yyyy hh:mm").parse(dateString))
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
              BadRequest(message.error("Expecting date"))
            }
          }.getOrElse{
            BadRequest(message.error("Expecting type"))
          }
        }.getOrElse{
          BadRequest(message.error("Expecting name"))
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
      (json \ "name").asOpt[String].map{name =>
        (json \ "type").asOpt[String].map{categoryType =>
          try{
            val obj = new Category(name, categoryType)
            Created(model.save(obj))
          }
          catch {
            case e: Exception =>
              InternalServerError(message.error(e.getLocalizedMessage))
          }
        }.getOrElse{
          BadRequest(message.error("Expecting type"))
        }
      }.getOrElse{
        BadRequest(message.error("Expecting name"))
      }
    }.getOrElse{
      BadRequest(message.error("Expecting category data"))
    }
  }

  def delete(id: String) = Action{
    try{
      Ok(message.success(model.delete(id)))
    }
    catch {
      case e: Exception =>
        InternalServerError(message.error(e.getLocalizedMessage))
    }
  }
}
