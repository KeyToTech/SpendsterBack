package services.auth

import javax.inject.Inject
import play.api.http.HeaderNames
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

case class UserRequest[A](token: String, request: Request[A]) extends WrappedRequest[A](request)

class AuthAction @Inject()(bodyParser: BodyParsers.Default)(implicit ec: ExecutionContext)
  extends ActionBuilder[UserRequest, AnyContent]{
  override def parser: BodyParser[AnyContent] = bodyParser
  override protected def executionContext: ExecutionContext = ec

  override def invokeBlock[A](request: Request[A], block: UserRequest[A] => Future[Result]): Future[Result] = {
    extractToken(request) map{token =>
      if(validateToken(token)){
        block(UserRequest(token, request))
      }else{
        Future.successful(Results.Unauthorized("Invalid token"))
      }
    } getOrElse{
      Future.successful(Results.Unauthorized)
    }
  }

  private def extractToken[A](request: Request[A]): Option[String] = {
    request.headers.get(HeaderNames.AUTHORIZATION)
  }

  private def validateToken(token: String): Boolean ={
    token.length > 1
  }
}
