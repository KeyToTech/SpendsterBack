package controllers

import java.util.Date

import domain.entity.Category
import domain.models.CategoryModel
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}


class CategoryController @Inject()(cc: ControllerComponents,
                                   model: CategoryModel)
  extends AbstractController(cc){

  def getAll = Action {
    try{
      Ok(model.getAll)
    }
    catch {
      case e: Exception =>
        InternalServerError(e.getLocalizedMessage)
    }
  }

  def getOne(id: String) = Action {
    try{
      Ok(model.getOne(id))
    }
    catch {
      case e: Exception =>
        InternalServerError(e.getLocalizedMessage)
    }
  }

  def update = Action {implicit request =>
    request.body.asJson.map {json =>
      (json \ "id").asOpt[String].map{id =>
        (json \ "name").asOpt[String].map{name =>
          (json \ "type").asOpt[String].map{cType =>
            (json \ "CreatedDate").asOpt[Date].map{date =>
              try{
                val obj = new Category
                obj.setId(id)
                obj.setName(name)
                obj.setType(cType)
                obj.setCreatedDate(date)

                Ok(model.update(obj))
              }
              catch {
                case e: Exception =>
                  InternalServerError(e.getLocalizedMessage)
              }
            }.getOrElse{
              BadRequest("Expecting date")
            }
          }.getOrElse{
            BadRequest("Expecting type")
          }
        }.getOrElse{
          BadRequest("Expecting name")
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
      (json \ "name").asOpt[String].map{name =>
        (json \ "type").asOpt[String].map{cType =>
          (json \ "CreatedDate").asOpt[Date].map{date =>
            try{
              val obj = new Category
              obj.setId("")
              obj.setName(name)
              obj.setType(cType)
              obj.setCreatedDate(date)

              Ok(model.save(obj))
            }
            catch {
              case e: Exception =>
                InternalServerError(e.getLocalizedMessage)
            }
          }.getOrElse{
            BadRequest("Expecting date")
          }
        }.getOrElse{
          BadRequest("Expecting type")
        }
      }.getOrElse{
        BadRequest("Expecting name")
      }
    }.getOrElse{
      BadRequest("Expecting category data")
    }
  }

  def delete(id: String) = Action{
    try{
      Ok(model.delete(id))
    }
    catch {
      case e: Exception =>
        InternalServerError(e.getLocalizedMessage)
    }
  }
}
