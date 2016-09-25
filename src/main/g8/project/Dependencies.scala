import sbt._

object Dependencies {
	
	object Versions {
		val scala           = "$scala_version$"
		val scalatra        = "$scalatra_version$"
		val logback         = "1.1.5"
		val jetty           = "9.2.10.v20150310"
		val servlet         = "3.1.0"
		val newRelic        = "$new_relic_version$"
		
		val scalatest       = "3.0.0"
		val scalamock       = "3.2.2"
		val mockito         = "1.10.19"
		
		val madonnaConfig   = "0.0.9"
	}
	
	// Repositories
	lazy val repositories = Seq(
		"Nexus releases"              at  "http://nexus.despegar.it/nexus/content/repositories/releases/",
		"Nexus Snapshots"             at  "http://nexus.despegar.it/nexus/content/repositories/snapshots/",
		"Nexus despegar miami"        at  "http://nexus:8080/nexus/content/groups/public/"
	)
	
	// Dependencies
	lazy val commonDeps = Seq(
		"org.scala-lang"              %  "scala-compiler"        % Versions.scala,
		"ch.qos.logback"              %  "logback-classic"       % Versions.logback            % "runtime",
		"com.newrelic.agent.java"     %  "newrelic-api"          % Versions.newRelic,
		"com.newrelic.agent.java"     %  "newrelic-agent"        % Versions.newRelic,
		"org.scalatest"               %% "scalatest"             % Versions.scalatest          % "test",
		"org.scalamock"               %% "scalamock-scalatest-support" % Versions.scalamock    % "test",
		"org.mockito"                 %  "mockito-all"           % Versions.mockito            % "test",
		"javax.servlet"               % "javax.servlet-api"      % Versions.servlet            % "provided;compile;test",
		"org.eclipse.jetty"           % "jetty-server"           % Versions.jetty,
		"org.eclipse.jetty"           % "jetty-webapp"           % Versions.jetty              % "container;compile"
	)
	
	lazy val scalatraDeps = Seq(
		"org.scalatra"                %% "scalatra"              % Versions.scalatra,
		"org.scalatra"                %% "scalatra-json"         % Versions.scalatra,
		"org.scalatra"                %% "scalatra-swagger"      % Versions.scalatra,
		"org.scalatra"                %% "scalatra-scalate"      % Versions.scalatra,
		"org.scalatra"                %% "scalatra-specs2"       % Versions.scalatra            % "test"
	)
	
	lazy val despegarDeps = Seq(
		"com.despegar.sbt"            %% "madonna-configuration" % Versions.madonnaConfig
	)
	
	
	lazy val appDependencies = commonDeps ++ scalatraDeps ++ despegarDeps

}