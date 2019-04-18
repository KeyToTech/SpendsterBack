package controllers

import java.util.Date

import com.google.gson.Gson
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}


class CategoryController @Inject()(cc: ControllerComponents,
                                   //TODO: add repo here and uncomment returns in methods https://trello.com/c/wWclhRQx/103-add-category-repository
                                   gson: Gson)
  extends AbstractController(cc){

  def getAll = Action {
    //Ok(repo.getAll())
    throw new UnsupportedOperationException()
    Ok("")
  }

  def getOne(id: String) = Action {
    //Ok(repo.getOne(id))
    throw new UnsupportedOperationException()
    Ok("")
  }

  def update = Action {implicit request =>
    request.body.asJson.map {json =>
      (json \ "id").asOpt[Int].map{id =>
        (json \ "name").asOpt[String].map{name =>
          (json \ "type").asOpt[String].map{cType =>
            (json \ "CreatedDate").asOpt[Date].map{date =>
              //Ok(repo.update(id, name, cType, date))
              throw new UnsupportedOperationException()
              Ok("")
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
            //Ok(repo.save(name, cType, date))
            throw new UnsupportedOperationException()
            Ok("")
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
    //Ok(repo.delete(id))
    throw new UnsupportedOperationException()
    Ok("")
  }
}
