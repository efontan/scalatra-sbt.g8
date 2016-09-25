import com.despegar.sbt.madonna.Madonna.MadonnaKeys
import sbt.Keys._
import sbt._
import sbtrelease.ReleasePlugin.autoImport._
import sbtrelease.ReleaseStateTransformations._

import scala.concurrent.duration._

object Release {
	
	lazy val releaseSettings = Seq(
		
		publishTo := {
			val nexus = "http://nexus.despegar.it:8080/nexus/"
			if (version.value.endsWith("SNAPSHOT")) {
				Some("snapshots" at nexus + "content/repositories/snapshots")
			} else {
				Some("releases" at nexus + "content/repositories/releases")
			}
		},
		
		mainClass := Some("$package$.startup.$name;format="Camel"$App"),
		
		publish in ThisBuild := Seq(MadonnaKeys.tarPublish),
		
		publishLocal in ThisBuild := Seq(MadonnaKeys.tarPublishLocal),
		
		MadonnaKeys.shutdownTimeout := 30.seconds,
		
		MadonnaKeys.healthCheckURI := "/$name;format="norm"$/health-check",
		
		releaseTagName := (version in ThisBuild).value,
		
		// release steps
		releaseProcess := Seq[ReleaseStep](
			checkSnapshotDependencies,
			inquireVersions,
			runTest,
			setReleaseVersion,
			commitReleaseVersion,
			tagRelease,
			setNextVersion,
			commitNextVersion,
			pushChanges
		),
		
		scalacOptions ++= Seq(
			"-unchecked",
			"-deprecation",
			"-Xlint",
			"-Ywarn-dead-code",
			"-Ywarn-unused",
			"-Ywarn-unused-import",
			"-language:_",
			"-target:jvm-1.7",
			"-encoding", "UTF-8"
		)
	)
}
