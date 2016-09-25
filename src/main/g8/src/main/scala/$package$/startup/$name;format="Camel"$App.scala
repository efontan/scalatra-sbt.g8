package $package$.startup

import org.eclipse.jetty.server.{HttpConfiguration, HttpConnectionFactory, Server, ServerConnector}
import org.eclipse.jetty.servlet.DefaultServlet
import org.eclipse.jetty.util.thread.QueuedThreadPool
import org.eclipse.jetty.webapp.WebAppContext
import org.scalatra.servlet.ScalatraListener

object $name;format="Camel"$App extends App {
	
	import $package$.config.JettyConfig._
	
	val threadPool = new QueuedThreadPool(maxThreads, minThreads)
	val server = new Server(threadPool)
	
	// HTTP Factory
	val httpConfiguration = new HttpConfiguration
	httpConfiguration.setSendServerVersion(false)
	val httpFactory = new HttpConnectionFactory(httpConfiguration)
	
	server.addConnector {
		val httpConnector = new ServerConnector(server, httpFactory)
		httpConnector.setPort(port)
		httpConnector.setIdleTimeout(idleTimeout)
		httpConnector
	}
	
	server.setHandler {
		val webContext = new WebAppContext
		// to disable directory listing in Jetty
		webContext.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false")
		
		webContext.setContextPath(contextPath)
		webContext.setResourceBase("src/main/webapp")
		webContext.addEventListener(new ScalatraListener)
		webContext.addServlet(classOf[DefaultServlet], "/")
		
		webContext
	}
	
	server.start()
	sys.addShutdownHook {
		server.stop()
	}
	server.join()
}