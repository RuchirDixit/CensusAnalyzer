// Copyright (C) 2011-2012 the original author or authors.
// See the LICENCE.txt file distributed with this work for additional
// information regarding copyright ownership.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.censusAnalyzer.censusutils

import java.nio.file.{Files, Paths}
import java.util

import com.censusAnalyzer.csvutils.CSVBuilderFactory.createCSVBuilder
import com.censusAnalyzer.censusutils.CensusLoader.checkFileProperties
import com.censusAnalyzer.models.{CensusDAO, USCensusData}
import com.typesafe.scalalogging.LazyLogging

import scala.collection.convert.ImplicitConversions.`collection AsScalaIterable`

object USCensusDataAnalyser extends LazyLogging {

  var table: util.List[USCensusData] = new util.ArrayList()
  var map: Map[String, CensusDAO] = Map()

  /**
   * To load US Census Data
   * @param path : path of file
   * @return : Count of records
   */
  def loadUSCensusData(path: String = "asset/USCensusData.csv"): Int = {
    checkFileProperties(path, Array[String]("State Id",
      "State", "Population", "Housing units", "Total area", "Water area", "Land area", "Population Density", "Housing Density"))
    val readerStateCensus = Files.newBufferedReader(Paths.get(path))
    table = createCSVBuilder().fetchList(readerStateCensus, classOf[USCensusData])
    loadUSCensusDataAsMap(path)
    table.size()
  }

  /**
   * To load Us Census data as map
   * @param path : path of file
   */
  def loadUSCensusDataAsMap(path: String = "asset/USCensusData.csv"): Unit = {
    map = table.map(item => (item.state, new CensusDAO(item))).toMap
  }

  /**
   * To sort US Census data by column index
   * @param column : index
   */
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

  // To sort State census data by state name
  def sortUSCensusDataByStateName(): Unit = {
    sortUSCensusDataByColumnIndex(1)
  }

  // To sort State census data by population
  def sortUSCensusDataByPopulation(): Unit = {
    sortUSCensusDataByColumnIndex(2)
  }

  // To print US Census data
  def printUSCensusData(): Unit = {
    for (index <- 0 until table.size()) {
      logger.info(table.get(index).toString)
    }
  }
}
