import com.google.inject.AbstractModule
import java.time.Clock

import com.google.gson.{Gson, GsonBuilder}
import com.google.inject.binder.ScopedBindingBuilder
import domain.models._
import domain.models.impl._
import domain.repositories._
import domain.repositories.impl._
import domain.repositories.mocked.MockedCategoryRepository
import services.{ApiJsonMessage, ApplicationTimer, AtomicCounter, Counter}

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

  private def bindModels: ScopedBindingBuilder = {
    bind(classOf[UserModel]).to(classOf[SimpleUserModel])
    bind(classOf[CategoryModel]).to(classOf[SimpleCategoryModel])
  }

  private def bindRepositories: ScopedBindingBuilder = {
    bind(classOf[UserRepository]).to(classOf[SimpleUserRepository])
  }

  private def bindMockedRepositories = {
    bind(classOf[CategoryRepository]).to(classOf[MockedCategoryRepository])
  }
}
