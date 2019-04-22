package controllers

import java.text.{ParseException, SimpleDateFormat}
import java.util.Date

import domain.entity.Category
import domain.models.CategoryModel
import javax.inject.Inject
import services.ApiJsonMessage
import play.api.mvc.{AbstractController, ControllerComponents}


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
        (json \ "name").asOpt[String].map{name =>
          (json \ "type").asOpt[String].map{cType =>
            (json \ "CreatedDate").asOpt[String].map{dateString =>
              try{
                val obj = new Category(id, name, cType,
                  new SimpleDateFormat("dd/mm/yyyy hh:mm").parse(dateString))

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
              BadRequest(message.create("Expecting date"))
            }
          }.getOrElse{
            BadRequest(message.create("Expecting type"))
          }
        }.getOrElse{
          BadRequest(message.create("Expecting name"))
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
      (json \ "name").asOpt[String].map{name =>
        (json \ "type").asOpt[String].map{cType =>
          try{
            val obj = new Category(new String, name, cType, new Date)

            Created(model.save(obj))
          }
          catch {
            case e: Exception =>
              InternalServerError(message.create(e.getLocalizedMessage.replace("\"", "'")))
          }
        }.getOrElse{
          BadRequest(message.create("Expecting type"))
        }
      }.getOrElse{
        BadRequest(message.create("Expecting name"))
      }
    }.getOrElse{
      BadRequest(message.create("Expecting category data"))
    }
  }

  def delete(id: String) = Action{
    try{
      Ok(model.delete(id))
    }
    catch {
      case e: Exception =>
        InternalServerError(message.create(e.getLocalizedMessage.replace("\"", "'")))
    }
  }
}
