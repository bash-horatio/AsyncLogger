package com.bodao.common

import java.io.File

import scala.io.{BufferedSource, Source}

/**
 * adapted from ConfigHelper by dgl
 */
object ConfigTool {

  var conf: Option[DynConfig] = None
  def getConf(): DynConfig = {
    conf match {
      case None =>
        val conf_ = DynConfigFactory.load("./conf/dynamic.conf")
        conf = Some(conf_)
        conf_
      case Some(conf) => conf
    }
  }

}

object DynConfigFactory {
  def exists(fileName: String): Boolean = {
    val file = new File(fileName)
    if (file.exists()) true else false
  }

  def getFileLinesTrim(fileName: String): Option[Array[String]] = {
    var source: BufferedSource = null
    var ret: Option[Array[String]] = None

    try {
      source = Source.fromFile(fileName)
      val lines = source.getLines()
      /* 去除首尾空格后，如果长度大于0，证明有内容 */
      val fileLines = lines.map(line => line.trim).filter(line => line.length > 0)
      ret = Some(fileLines.toArray)
    } catch {
      case ex: Exception =>
        AsyncLogger.error(this.getClass, "failed to trim lines", ex)
        ret = None
    } finally {
      if (source != null) source.close()
    }
    ret
  }

  def load(configFile: String): DynConfig = {
    val noConfig = new DynConfig(Array[String]())	/* null */
    if (!this.exists(configFile)) noConfig

    else {
      val lines = this.getFileLinesTrim(configFile)
      lines match {
        case None =>
          AsyncLogger.error(this.getClass, "failed to trim lines")
          noConfig
        case Some(line) => new DynConfig(line)
      }

    }
  }
}


class DynConfig(kvs: Array[String]) {
  import scala.collection.mutable.{Map => muMap}
  val conf = muMap[String, String]()
  initConfing(kvs)

  def getString(key: String): String = {
    if (conf.contains(key))  conf(key).toString
    else Unit.toString
  }

  private def rmQuotes(str: String): String = {
    if (str.length > 0) {
      if (str.charAt(0) == '\"' && str.charAt(str.length - 1) == '\"')
         str.substring(1, str.length - 1)
      else str
    }else  str
  }

  private def initConfing(kvs: Array[String]) {
    kvs.foreach { kv =>
      if (kv.trim.length > 0 && kv.trim.charAt(0) != '#') {	/* remove '\n' and notation lines */
      val fields = kv.trim().split("=", 2)
        if (!conf.contains(fields(0).trim())) {
          conf += (fields(0).trim -> rmQuotes(fields(1).trim()))
        }
      }
    }
  }
}
