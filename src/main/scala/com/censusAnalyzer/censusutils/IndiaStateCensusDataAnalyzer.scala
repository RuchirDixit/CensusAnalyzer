
package com.censusAnalyzer.censusutils

import java.nio.file.{Files, Paths}
import java.util

import com.censusAnalyzer.csvutils.CSVBuilderFactory.createCSVBuilder
import com.censusAnalyzer.censusutils.CensusLoader.checkFileProperties
import com.censusAnalyzer.exception.CensusAnalyzerException
import com.censusAnalyzer.models.{CensusDAO, IndiaStateCensus}

import scala.collection.convert.ImplicitConversions.`collection AsScalaIterable`

object IndiaStateCensusDataAnalyser {

  var table: util.List[IndiaStateCensus] = new util.ArrayList()
  var map: Map[String, CensusDAO] = Map[String, CensusDAO]()

  @throws[CensusAnalyzerException]
  def loadIndiaStateCensusData(path: String = "asset/IndiaStateCensusData.csv"): Int = {

    checkFileProperties(path, Array[String]("State", "Population", "AreaInSqKm", "DensityPerSqKm"))

    val readerStateCensus = Files.newBufferedReader(Paths.get(path))
    table = createCSVBuilder().fetchList(readerStateCensus, classOf[IndiaStateCensus])
    loadIndiaStateCensusDataAsMap(path)

    table.size()
  }

  def loadIndiaStateCensusDataAsMap(path: String = "asset/IndiaStateCensusData.csv"): Unit = {

    map = table.map(item => (item.state, new CensusDAO(item))).toMap
  }

  def sortStateCensusDataByColumnIndex(column: Int): Unit = {
    util.Collections.sort(table, (o1: IndiaStateCensus, o2: IndiaStateCensus) => {
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

  def sortStateCensusDataByStateName(): Unit = {
    sortStateCensusDataByColumnIndex(0)
  }

  def sortStateCensusDataByPopulation(): Unit = {
    sortStateCensusDataByColumnIndex(1)
  }

  def sortStateCensusDataByArea(): Unit = {
    sortStateCensusDataByColumnIndex(2)
  }

  def printStateCensusData(): Unit = {
    for (index <- 0 until table.size()) {
      println(table.get(index))
    }
  }
}