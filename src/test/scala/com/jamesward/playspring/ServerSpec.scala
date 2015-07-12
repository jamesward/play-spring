package com.jamesward.playspring

import java.io.File

import com.typesafe.config.ConfigFactory
import scala.collection.JavaConverters._
import play.api.routing.Router
import play.core.server._
import play.api._
import play.api.routing.sird._
import play.api.mvc._

import org.scalatest.{BeforeAndAfter, Matchers, FlatSpec}




class ServerSpec extends FlatSpec with Matchers with BeforeAndAfter {

  val components = new NettyServerComponents with BuiltInComponents {

    lazy val router = Router.from {
      case GET(p"/hello/$to") => Action {
        Results.Ok(s"Hello $to")
      }
    }

    val baseConfigMap: Map[String, String] = Map(
      "play.server.dir" -> "/dev/null",
      "play.application.loader" -> "com.jamesward.playspring.SpringApplicationLoader"
    )

    lazy val baseConfig = ConfigFactory.parseMap(baseConfigMap.asJava)

    lazy val defaultConfig = ConfigFactory.parseResources(getClass.getClassLoader, "reference.conf")


    override lazy val configuration = Configuration(baseConfig.withFallback(defaultConfig).resolve())


    override lazy val serverConfig: ServerConfig = ServerConfig(
      port = Some(19000),
      address = "127.0.0.1",
      configuration = configuration,
      rootDir = new File("."),
      sslPort = None,
      mode = Mode.Test,
      properties = System.getProperties
    )

  }

  val server = components.server

  after {
    server.stop()
  }

  "A Server" should "start" in {

    server.applicationProvider.get.get.configuration.getString("play.application.loader") should equal (Some("com.jamesward.playspring.SpringApplicationLoader"))

    server.httpPort should equal (Some(19000))

  }

}
