import java.time.Clock

import com.google.gson.{Gson, GsonBuilder}
import com.google.inject.AbstractModule
import domain.models._
import domain.models.impl._
import domain.repositories._
import domain.repositories.mocked._
import services._
import services.auth.BearerTokenGenerator

/**
 * This class is a Guice module that tells Guice how to bind several
 * different types. This Guice module is created when the Play
 * application starts.

 * Play will automatically use any class called `Module` that is in
 * the root package. You can create modules in other locations by
 * adding `play.modules.enabled` settings to the `application.conf`
 * configuration file.
 */
class Module extends AbstractModule {

  override def configure(): Unit = {

    val gson = new GsonBuilder().enableComplexMapKeySerialization().create()
    bind(classOf[Gson]).toInstance(gson)

    val message = new ApiJsonMessage
    bind(classOf[ApiJsonMessage]).toInstance(message)

    val tokenGenerator = new BearerTokenGenerator
    bind(classOf[BearerTokenGenerator]).toInstance(tokenGenerator)

    this.bindRepositories
    this.bindMockedRepositories
    this.bindModels

    // Use the system clock as the default implementation of Clock
    bind(classOf[Clock]).toInstance(Clock.systemDefaultZone)
    // Ask Guice to create an instance of ApplicationTimer when the
    // application starts.
    bind(classOf[ApplicationTimer]).asEagerSingleton()
    // Set AtomicCounter as the implementation for Counter.
    bind(classOf[Counter]).to(classOf[AtomicCounter])
  }

  private def bindModels = {
    bind(classOf[UserModel]).to(classOf[SimpleUserModel])
    bind(classOf[CategoryModel]).to(classOf[SimpleCategoryModel])
    bind(classOf[ExpensesModel]).to(classOf[SimpleExpensesModel])
  }

  private def bindRepositories = {
    bind(classOf[UserRepository]).to(classOf[MockedUserRepository])
  }

  private def bindMockedRepositories = {
    bind(classOf[CategoryRepository]).to(classOf[MockedCategoryRepository])
    bind(classOf[ExpensesRepository]).to(classOf[MockedExpensesRepository])
  }
}
