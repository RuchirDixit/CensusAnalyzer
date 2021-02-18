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
import com.censusAnalyzer.exception.CensusAnalyzerException
import com.censusAnalyzer.models.{CensusDAO, IndiaStateCensus}
import com.typesafe.scalalogging.LazyLogging

import scala.collection.convert.ImplicitConversions.`collection AsScalaIterable`

object IndiaStateCensusDataAnalyser extends LazyLogging{

  var table: util.List[IndiaStateCensus] = new util.ArrayList()
  // Map of string and CensusDAO
  var map: Map[String, CensusDAO] = Map[String, CensusDAO]()

  /**
   * To load India State Census Data
   * @param path : Path of file passed
   * @throws CensusAnalyzerException
   * @return : Count of records
   */
  @throws[CensusAnalyzerException]
  def loadIndiaStateCensusData(path: String = "asset/IndiaStateCensusData.csv"): Int = {

    checkFileProperties(path, Array[String]("State", "Population", "AreaInSqKm", "DensityPerSqKm"))

    val readerStateCensus = Files.newBufferedReader(Paths.get(path))
    table = createCSVBuilder().fetchList(readerStateCensus, classOf[IndiaStateCensus])
    loadIndiaStateCensusDataAsMap(path)

    table.size()
  }

  /**
   * To load Indis State Census Data As map
   * @param path : Path of file
   */
  def loadIndiaStateCensusDataAsMap(path: String = "asset/IndiaStateCensusData.csv"): Unit = {

    map = table.map(item => (item.state, new CensusDAO(item))).toMap
  }

  /**
   * To sort State census Data by column index
   * @param column : Index number
   */
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

  // To sort State census data by state name
  def sortStateCensusDataByStateName(): Unit = {
    sortStateCensusDataByColumnIndex(0)
  }

  // To sort State census data by population
  def sortStateCensusDataByPopulation(): Unit = {
    sortStateCensusDataByColumnIndex(1)
  }

  // To sort State census data by area
  def sortStateCensusDataByArea(): Unit = {
    sortStateCensusDataByColumnIndex(2)
  }

  // To print state census data
  def printStateCensusData(): Unit = {
    for (index <- 0 until table.size()) {
      logger.info(table.get(index).toString)
    }
  }
}
