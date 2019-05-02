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
        InternalServerError(message.create(e.getLocalizedMessage))
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
        (json \ "name").asOpt[String].map{name =>
          (json \ "type").asOpt[String].map{categoryType =>
            (json \ "imgLink").asOpt[String].map { imgLink =>
              (json \ "CreatedDate").asOpt[String].map { dateString =>
                try {
                  val obj = new Category(id, name, categoryType, imgLink, new SimpleDateFormat("dd/M/yyyy hh:mm").parse(dateString))
                  Ok(model.update(obj))
                }
                catch {
                  case e: ParseException =>
                    BadRequest(message.create(e.getLocalizedMessage +
                      " Date format: dd/mm/yyyy hh:mm"))
                  case e: Exception =>
                    InternalServerError(message.create(e.getLocalizedMessage))
                }
              }.getOrElse {
                BadRequest(message.create("Expecting date"))
              }
            }.getOrElse{
              BadRequest(message.create("Expecting imgLink"))
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
        (json \ "type").asOpt[String].map{categoryType =>
          (json \ "imgLink").asOpt[String].map {imgLink =>
            try {
              val obj = new Category(name, categoryType, imgLink)
              Created(model.save(obj))
            }
            catch {
              case e: Exception =>
                InternalServerError(message.create(e.getLocalizedMessage))
            }
          }.getOrElse{
            BadRequest(message.create("Expecting imgLink"))
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
      Ok(model.delete(id).toString)
    }
    catch {
      case e: Exception =>
        InternalServerError(message.create(e.getLocalizedMessage))
    }
  }
}
