# AsyncLogger
### Async Log System using Akka Actor Model
 * Copyright (c) 2016/01/09, BoDao Tech, Inc. All Rights Reserved.
 * Author @ bash.horatio@gmai.com
 * Async Log System using Akka Actor Model
 * flushToFile: flush to File and clear Log Buffer
 * error, warning, info, debug: send different log level events to Log Buffer
 

### APIs: 
```
flushToFile
error(logClass: Class[_], message: String, cause: Throwable)
error(logClass: Class[_], message: String)
warning(logClass: Class[_], message: String)
info(logClass: Class[_], message: String)
debug(logClass: Class[_], message: String)
```
