# AsyncLogger
## Async Log System using Akka Actor Model
 * Copyright (c) 2016/01/09, BoDao Tech, Inc. All Rights Reserved.
 * Author @ bash.horatio@gmai.com
 * Async Log System using Akka Actor Model
 * flushToFile: flush to File and clear Log Buffer
 * error, warning, info, debug: send different log level events to Log Buffer

APIs: 
```
  def flushToFile = logger ! Flush(system.getClass, "flush and clear immediately")
  def error(logClass: Class[_], message: String, cause: Throwable) = logger ! Error(logClass, message, cause)
  def error(logClass: Class[_], message: String) = logger ! new Error(logClass, message)
  def warning(logClass: Class[_], message: String) = logger ! Warning(logClass, message)
  def info(logClass: Class[_], message: String) = logger ! Info(logClass, message)
  def debug(logClass: Class[_], message: String) = logger ! Debug(logClass, message)
```
