package data.config

/**
  * StagingDBConfig
  *
  * @author Bohdan Ustiak (bogdan.ustyak@gmail.com)
  */
class StagingConnectionConfig extends ConnectionConfig {

  override def dbName() = {
    "spendster"
  }

  override def userName ()= {
    "spendster_user"
  }

  override def password ()= {
    "EZ7FdcDvusntqwr2"
  }

  override def host() = {
    "ds147072.mlab.com"
  }

  override def port() = {
    "47072"
  }
}