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
import com.censusAnalyzer.models.{CensusDAO, IndiaStateCode}
import com.typesafe.scalalogging.LazyLogging

import scala.collection.convert.ImplicitConversions.`collection AsScalaIterable`

object IndiaStateCodeAnalyser extends LazyLogging {

  var table: util.List[IndiaStateCode] = new util.ArrayList()
  var map: Map[String, CensusDAO] = Map()

  /**
   * To load india state code data
   * @param path : path of file
   * @return : Count of records
   */
  def loadIndiaStateCode(path: String = "asset/IndiaStateCode.csv"): Int = {
    checkFileProperties(path, Array[String]("SrNo", "State Name", "TIN", "StateCode"))
    val readerStateCensus = Files.newBufferedReader(Paths.get(path))
    table = createCSVBuilder().fetchList(readerStateCensus, classOf[IndiaStateCode])
    loadIndiaStateCodeAsMap(path)
    table.size()
  }

  /**
   * To load India state code data
   * @param path : path of file
   */
  def loadIndiaStateCodeAsMap(path: String = "asset/IndiaStateCode.csv"): Unit = {
    map = table.map(item => (item.state, new CensusDAO(item))).toMap
  }

  /**
   * To sort State code data by column index
   * @param column : index
   */
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

  // To sort State code data by name
  def sortStateCodeByStateName(): Unit = {
    sortStateCodeByColumnIndex(1)
  }

  // To print State code data
  def printStateCode(): Unit = {
    for (index <- 0 until table.size()) {
      logger.info(table.get(index).toString)
    }
  }
}
