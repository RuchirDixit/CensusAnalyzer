
package com.censusAnalyzer.censusutils

import java.nio.file.{Files, Paths}
import java.util

import com.censusAnalyzer.csvutils.CSVBuilderFactory.createCSVBuilder
import com.censusAnalyzer.censusutils.CensusLoader.checkFileProperties
import com.censusAnalyzer.models.{CensusDAO, USCensusData}

import scala.collection.convert.ImplicitConversions.`collection AsScalaIterable`

object USCensusDataAnalyser {

  var table: util.List[USCensusData] = new util.ArrayList()
  var map: Map[String, CensusDAO] = Map()

  def loadUSCensusData(path: String = "asset/USCensusData.csv"): Int = {

    checkFileProperties(path, Array[String]("State Id", "State", "Population", "Housing units", "Total area", "Water area", "Land area", "Population Density", "Housing Density"))

    val readerStateCensus = Files.newBufferedReader(Paths.get(path))
    table = createCSVBuilder().fetchList(readerStateCensus, classOf[USCensusData])
    loadUSCensusDataAsMap(path)

    table.size()
  }

  def loadUSCensusDataAsMap(path: String = "asset/USCensusData.csv"): Unit = {
    map = table.map(item => (item.state, new CensusDAO(item))).toMap
  }

  def sortUSCensusDataByColumnIndex(column: Int): Unit = {
    util.Collections.sort(table, (o1: USCensusData, o2: USCensusData) => {
      try {
        val o1Int = o1.get(column).asInstanceOf[Double]
        val o2Int = o2.get(column).asInstanceOf[Double]
        o1Int.compareTo(o2Int)
      }
      catch {
        case _: Exception =>
          try{
            val o1Int = o1.get(column).asInstanceOf[Integer]
            val o2Int = o2.get(column).asInstanceOf[Integer]
            o1Int.compareTo(o2Int)
          }
          catch{
            case e:Exception =>
              o1.get(column).asInstanceOf[String].compareTo(o2.get(column).asInstanceOf[String])
          }
      }
    })
  }

  def sortUSCensusDataByStateName(): Unit = {
    sortUSCensusDataByColumnIndex(1)
  }

  def sortUSCensusDataByPopulation(): Unit = {
    sortUSCensusDataByColumnIndex(2)
  }

  def printUSCensusData(): Unit = {
    for (index <- 0 until table.size()) {
      println(table.get(index))
    }
  }
}