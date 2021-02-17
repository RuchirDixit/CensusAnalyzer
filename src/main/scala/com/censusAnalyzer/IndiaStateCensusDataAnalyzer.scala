package com.censusAnalyzer

import java.util
import com.censusAnalyzer.CensusAnalyzer.loadCSVData
import com.censusAnalyzer.exception.CensusAnalyzerException

object IndiaStateCensusDataAnalyser {

  var table: util.ArrayList[util.ArrayList[String]] = new util.ArrayList()

  @throws[CensusAnalyzerException]
  def loadIndiaStateCensusData(path: String = "asset/IndiaStateCensusData.csv"): Int = {
    val indiaStateCensusDataPath = path
    table = loadCSVData(indiaStateCensusDataPath, Array("state", "population", "areainsqkm", "densitypersqkm"))
    table.size() - 1
  }

  def sortStateCensusDataByColumnIndex(column: Int): Unit = {
    util.Collections.sort(table, (o1: util.ArrayList[String], o2: util.ArrayList[String]) => {
      try {
        val o1Int = Integer.parseInt(o1.get(column))
        val o2Int = Integer.parseInt(o2.get(column))
        o1Int.compareTo(o2Int)
      }
      catch{
        case e:Exception =>
          o1.get(column).compareTo(o2.get(column))
      }
    })
  }

  def sortStateCensusDataByStateName(): Unit ={
    sortStateCensusDataByColumnIndex(0)
  }

  def printStateCensusData(): Unit ={
    for(index <- 0 until table.size()){
      for(element <- 0 until table.get(index).size()){
        print(table.get(index).get(element) + " ")
      }
      println()
    }
  }
}
