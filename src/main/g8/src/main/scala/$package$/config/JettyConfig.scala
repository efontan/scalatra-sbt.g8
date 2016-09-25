package $package$.config

import com.despegar.sbt.madonna.MadonnaConf

object JettyConfig {

  private lazy val config = MadonnaConf.config

  val port = config.getInt("jetty.port")
  val contextPath = config.getString("jetty.context-path")
  val maxThreads = config.getInt("jetty.max-threads")
  val minThreads = config.getInt("jetty.min-threads")
  val idleTimeout = config.getInt("jetty.idle-timeout")
}
