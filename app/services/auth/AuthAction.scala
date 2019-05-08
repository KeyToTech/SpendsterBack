package services.auth

import javax.inject.Inject
import play.api.http.HeaderNames
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

class AuthAction @Inject()(bodyParser: BodyParsers.Default)(implicit ec: ExecutionContext)
  extends ActionBuilder[UserRequest, AnyContent]{

  override def parser: BodyParser[AnyContent] = bodyParser
  override protected def executionContext: ExecutionContext = ec

  override def invokeBlock[A](request: Request[A], block: UserRequest[A] => Future[Result]): Future[Result] = {
    extractToken(request) map{token =>
      Try(getUserIdByToken(token)) match {
        case Success(value) => block(UserRequest(value, request))
        case Failure(exception) => Future.successful(Results.Unauthorized(exception.getLocalizedMessage))
      }
    } getOrElse{
      Future.successful(Results.Unauthorized("Expecting token"))
    }
  }

  private def extractToken[A](request: Request[A]): Option[String] = {
    request.headers.get(HeaderNames.AUTHORIZATION)
  }

  private def getUserIdByToken(token: String): String ={
    if(token.length > 1){
      "some-id"
    }else{
      throw new Exception("Invalid token")
    }
  }
}
