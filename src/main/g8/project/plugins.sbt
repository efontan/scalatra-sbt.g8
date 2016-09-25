resolvers ++= Seq(
	Resolver.typesafeRepo("releases"),
	Resolver.sonatypeRepo("public"),
	"Nexus despegar miami" at "http://nexus:8080/nexus/content/groups/public/"
)

addSbtPlugin("com.mojolly.scalate" % "xsbt-scalate-generator" % "0.5.0")

addSbtPlugin("org.scalatra.sbt" % "scalatra-sbt" % "0.5.0")

// Scalastyle usage http://www.scalastyle.org/sbt.html
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.8.0")

// Scalariform (https://github.com/sbt/sbt-scalariform)
addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.3.0")

// sbt-buildinfo (https://github.com/sbt/sbt-buildinfo)
addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.2.5")

// https://github.com/sbt/sbt-release
addSbtPlugin("com.github.gseitz"  %% "sbt-release" % "1.0.3")

addSbtPlugin("com.despegar.sbt" %% "madonna" % "0.1.1")