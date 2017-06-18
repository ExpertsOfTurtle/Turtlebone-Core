import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.net.*
import ch.qos.logback.core.*
import ch.qos.logback.core.encoder.*
import ch.qos.logback.core.read.*
import ch.qos.logback.core.rolling.*
import ch.qos.logback.core.status.*
import com.vipshop.mercury.log.logback.MercuryLogbackAppender
import ch.qos.logback.classic.AsyncAppender
import ch.qos.logback.classic.filter.*

statusListener(OnConsoleStatusListener)

def props = new Properties()
props.load(this.getClass().getClassLoader().getResourceAsStream("properties/application.properties"))

def config = new ConfigSlurper().parse(props)

def env =  System.properties['spring.profiles.active'] ?: 'production'
def appenderList = []
def logLevel = ERROR

def appName = config.app.name
def instanceName =  System.properties['app.instance.name'] ?: appName
def LOG_RECEIVER_DIR = '/apps/logs/log_receiver/'+ instanceName
def LOG_MERCURY_DIR = '/apps/logs/trace/logs/' + instanceName

jmxConfigurator()

if (env == 'production') {
    appenderList.add("ROLLING-ASYNC")
    logLevel = WARN
} else if(env == 'integratetest') {
    appenderList.add("ROLLING-ASYNC")
    logLevel = WARN
} else if(env == 'development') {
    appenderList.add("CONSOLE")
    appenderList.add("ROLLING-ASYNC")
    logLevel = INFO
}
if(env=='development') {
	appender("CONSOLE", ConsoleAppender) {
		encoder(PatternLayoutEncoder) {pattern = "[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%level] [%thread] [%logger{50}] >>> %msg%n" }
	}	
    logger("com.vip.hermes", ERROR, ["CONSOLE"])
    
   	logger("mercury-trace-logger", OFF, [], false)

	logger("mercury-log-logger", OFF, [], false)

}

if(env=='production' || env=='integratetest'  || env=='development') {
	appender("ROLLING", RollingFileAppender) {
	    file = "${LOG_RECEIVER_DIR}/application.log"
		encoder(PatternLayoutEncoder) {
		    pattern = "[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%level] [%thread] [%logger{50}] >>> %msg%n"
		}
		rollingPolicy(TimeBasedRollingPolicy) {
			fileNamePattern = "${LOG_RECEIVER_DIR}/application.%d{yyyy-MM-dd}.log"
		}
	}
	appender("ROLLING-ASYNC", AsyncAppender) {
    discardingThreshold = 0
    queueSize = 1024
    appenderRef("ROLLING")
    }
}

if(env=='production' || env=='integratetest') {
	
	appender("MERCURY", MercuryLogbackAppender) {
	  filter(ThresholdFilter){
		 level = "WARN"
	  } 
	}
	
    logger("com.vip", WARN, ["MERCURY"])
	
	appender("MercuryTraceFileAppender", RollingFileAppender) {
	  file = "${LOG_MERCURY_DIR}/trace-log.out"
	  rollingPolicy(FixedWindowRollingPolicy) {
	    fileNamePattern = "${LOG_MERCURY_DIR}/trace-log-%i.out"
	    minIndex = 1
	    maxIndex = 10
	  }
	  append = true
	  triggeringPolicy(SizeBasedTriggeringPolicy) {
	    maxFileSize = "1GB"
	  }
	  encoder(PatternLayoutEncoder) {
	    pattern = "%msg%n"
	  }
	}
	
	logger("mercury-trace-logger", INFO, ["MercuryTraceFileAppender"], false)
	
	appender("MercuryLogFileAppender", RollingFileAppender) {
	  file = "${LOG_MERCURY_DIR}/log-log.out"
	  rollingPolicy(FixedWindowRollingPolicy) {
	    fileNamePattern = "${LOG_MERCURY_DIR}/log-log-%i.out"
	    minIndex = 1
	    maxIndex = 10
	  }
	  append = true
	  triggeringPolicy(SizeBasedTriggeringPolicy) {
	    maxFileSize = "1GB"
	  }
	  encoder(PatternLayoutEncoder) {
	    pattern = "%msg%n"
	  }
	}
	
	logger("mercury-log-logger", INFO, ["MercuryLogFileAppender"], false)
	
	appender("MercuryMetricFileAppender", RollingFileAppender) {
	  file = "${LOG_MERCURY_DIR}/metric-log.out"
	  rollingPolicy(FixedWindowRollingPolicy) {
	    fileNamePattern = "${LOG_MERCURY_DIR}/metric-log-%i.out"
	    minIndex = 1
	    maxIndex = 10
	  }
	  append = true
	  triggeringPolicy(SizeBasedTriggeringPolicy) {
	    maxFileSize = "1GB"
	  }
	  encoder(PatternLayoutEncoder) {
	    pattern = "%msg%n"
	  }
	}
	logger("mercury-metric-logger", INFO, ["MercuryMetricFileAppender"], false)

}

root(logLevel, appenderList)