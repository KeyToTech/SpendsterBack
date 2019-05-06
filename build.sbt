name := "spendsterback"
 
version := "1.0" 
      
lazy val `spendsterback` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
      
scalaVersion := "2.12.8"

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice )

// https://mvnrepository.com/artifact/com.google.code.gson/gson
libraryDependencies += "com.google.code.gson" % "gson" % "2.8.5"

// https://mvnrepository.com/artifact/org.mongodb.morphia/morphia
libraryDependencies += "org.mongodb.morphia" % "morphia" % "1.3.2"

libraryDependencies ++= Seq(
  "org.mongodb.morphia" % "morphia" % "1.3.2",
  "org.mongodb" % "mongo-java-driver" % "3.2.2"
)

// https://mvnrepository.com/artifact/org.mongodb/mongodb-driver-sync
libraryDependencies += "org.mongodb" % "mongodb-driver-sync" % "3.9.0"

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )
