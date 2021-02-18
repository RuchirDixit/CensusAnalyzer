
package com.censusAnalyzer.censusutils

import java.nio.file.{Files, Paths}
import java.util

import com.censusAnalyzer.csvutils.CSVBuilderFactory.createCSVBuilder
import com.censusAnalyzer.censusutils.CensusLoader.checkFileProperties
import com.censusAnalyzer.models.{CensusDAO, IndiaStateCode}

import scala.collection.convert.ImplicitConversions.`collection AsScalaIterable`

object IndiaStateCodeAnalyser {

  var table: util.List[IndiaStateCode] = new util.ArrayList()
  var map: Map[String, CensusDAO] = Map()

  def loadIndiaStateCode(path: String = "asset/IndiaStateCode.csv"): Int = {

    checkFileProperties(path, Array[String]("SrNo", "State Name", "TIN", "StateCode"))

    val readerStateCensus = Files.newBufferedReader(Paths.get(path))
    table = createCSVBuilder().fetchList(readerStateCensus, classOf[IndiaStateCode])

    loadIndiaStateCodeAsMap(path)
    table.size()
  }

  def loadIndiaStateCodeAsMap(path: String = "asset/IndiaStateCode.csv"): Unit = {

    map = table.map(item => (item.state, new CensusDAO(item))).toMap
  }

  def sortStateCodeByColumnIndex(column: Int): Unit = {
    util.Collections.sort(table, (o1: IndiaStateCode, o2: IndiaStateCode) => {
      try {
        val o1Int = o1.get(column).asInstanceOf[Integer]
        val o2Int = o2.get(column).asInstanceOf[Integer]
        o1Int.compareTo(o2Int)
      }
      catch {
        case e: Exception =>
          o1.get(column).asInstanceOf[String].compareTo(o2.get(column).asInstanceOf[String])
      }
    })
  }

  def sortStateCodeByStateName(): Unit = {
    sortStateCodeByColumnIndex(1)
  }

  def printStateCode(): Unit = {
    for (index <- 0 until table.size()) {
      println(table.get(index))
    }
  }
}