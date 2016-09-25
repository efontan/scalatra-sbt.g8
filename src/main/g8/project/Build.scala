import com.despegar.sbt.madonna.Madonna
import com.earldouglas.xwp.JettyPlugin
import com.mojolly.scalate.ScalatePlugin.ScalateKeys._
import com.mojolly.scalate.ScalatePlugin._
import com.typesafe.sbt.SbtScalariform
import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import sbt.Keys._
import sbt._
import sbtbuildinfo.Plugin._

object $name;format="Camel"$Build extends Build {
	
	import Dependencies._
	import Release._
	
	lazy val root = Project("$name;format="norm"$", base = file("."))
	.settings(resolvers ++= repositories)
		.settings(libraryDependencies ++= Dependencies.appDependencies)
		.settings(commonSettings: _*)
		.settings(releaseSettings: _*)
		.settings(formatSettings: _*)
		.settings(org.scalastyle.sbt.ScalastylePlugin.projectSettings: _*)
		.settings(scalatePreferences: _*)
		.settings(buildInfoPreferences: _*)
		.enablePlugins(JettyPlugin)
		.enablePlugins(Madonna)
	
	lazy val commonSettings = Seq(
		organization := "$organization$",
		name := "$name;format="norm"$",
		version := "$version$",
		scalaVersion := "$scala_version$"
	)
	
	lazy val formatSettings = SbtScalariform.scalariformSettings ++ Seq(
		ScalariformKeys.preferences in compile := formattingPreferences,
		ScalariformKeys.preferences in test := formattingPreferences
	)
	
	def formattingPreferences =
		FormattingPreferences()
			.setPreference(RewriteArrowSymbols, false)
			.setPreference(AlignParameters, false)
			.setPreference(AlignSingleLineCaseStatements, true)
			.setPreference(DoubleIndentClassDeclaration, true)
	
	lazy val buildInfoPreferences = buildInfoSettings ++ Seq(
		sourceGenerators in Compile <+= buildInfo,
		buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
		buildInfoPackage := "$package$"
	)
	
	lazy val scalatePreferences = Seq(
		scalateTemplateConfig in Compile <<= (sourceDirectory in Compile){ base =>
			Seq(
				TemplateConfig(
					base / "webapp" / "WEB-INF" / "templates",
					Seq.empty, /* default imports should be added here */
					Seq(
						Binding("context", "_root_.org.scalatra.scalate.ScalatraRenderContext", importMembers = true, isImplicit = true)
					), /* add extra bindings here */
					Some("templates")
				)
			)
		}
	)
}
