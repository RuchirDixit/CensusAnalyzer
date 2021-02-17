package com.censusAnalyzer

import java.util

import com.censusAnalyzer.CensusAnalyzer.loadCSVData

object IndiaStateCodeAnalyser {

  var table: util.ArrayList[util.ArrayList[String]] = new util.ArrayList()

  def loadIndiaStateCode(path: String = "asset/IndiaStateCode.csv"): Int = {
    val indiaStateCodePath = path
    table = loadCSVData(indiaStateCodePath, Array("srno", "state name", "tin", "statecode"))
    table.size() - 1
  }

  def sortStateCodeByColumnIndex(column: Int): Unit = {
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

  def sortStateCodeByStateName(): Unit ={
    sortStateCodeByColumnIndex(1)
  }

  def printStateCode(): Unit ={
    for(index <- 0 until table.size()){
      for(element <- 0 until table.get(index).size()){
        print(table.get(index).get(element) + " ")
      }
      println()
    }
  }
}
