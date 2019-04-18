package controllers

import com.google.gson.Gson
import domain.entity.Expenses
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}


class ExpensesController @Inject()(cc: ControllerComponents,
                                   //TODO: add repo here and uncomment returns in methods https://trello.com/c/gJ2Nzjgc/104-add-expenses-repository
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
    val json = request.body.asJson.get.toString()
    val expenses = gson.fromJson(json, classOf[Expenses])
    //Ok(repo.update(category))
    throw new UnsupportedOperationException()
    Ok("")
  }

  def save = Action{implicit request =>
    val json = request.body.asJson.get.toString()
    val expenses = gson.fromJson(json, classOf[Expenses])
    //Ok(repo.save(category))
    throw new UnsupportedOperationException()
    Ok("")
  }

  def delete(id: String) = Action{
    //Ok(repo.delete(id))
    throw new UnsupportedOperationException()
    Ok("")
  }
}

