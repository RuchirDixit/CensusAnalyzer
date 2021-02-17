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
package com.censusAnalyzer.censusanalyzer

import com.censusAnalyzer.censusanalyzer.Country.Country
import com.google.gson.Gson
import com.typesafe.scalalogging.LazyLogging

/***
 * Class performs sorting and imports Gson dependency in order to convert data into
 * json and vice versa
 * used gson dependency added to build.sbt
 */
class CensusAnalyzer extends LazyLogging{
  /***
   * used Map collection to store key value pair
   */
  var censusMap: Map[String, CensusDAO] = Map()
  def loadCensusData(country: Country,filepath:String*): Int = {
    censusMap = new CensusLoader().loadData(country,filepath)
    logger.info("inside loadcensus data")
    censusMap.size
  }

  /***
   * Sort function sorts the object wrt to certain values
   * @param choice option between multiple value sort
   * @return sorted data
   */
  def sort(choice: Int): String = {
    if (censusMap == null || censusMap.isEmpty) {
      logger.error(CensusAnalyzerExceptionEnums.NoCensusData.toString)
      throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.NoCensusData)
    }
    // Array data structure to store CSV object
    var censusCSVList = censusMap.values.toArray
    censusCSVList = choice match {
      case 1 => censusCSVList.sortBy(_.state)
      case 2 => censusCSVList.sortBy(_.stateCode)
      case 3 => censusCSVList.sortBy(_.population).reverse
      case 4 => censusCSVList.sortBy(_.populationDensity).reverse
      case 5 => censusCSVList.sortBy(_.totalArea).reverse
    }
    val sortedStateCensusCensus = new Gson().toJson(censusCSVList)
    sortedStateCensusCensus
  }

  /***
   * Function calls sort function and tells to perform sorting wrt state wise census data
   *
   * @return string value of sorted data
   */
  def getStateWiseSortedCensusData: String = {
    logger.info("inside state wise census data")
    sort(1)
  }
  /***
   * Function calls sort function and tells to perform sorting wrt State code
   *
   * @return string value of sorted data
   */
  def getStateCodeWiseSortedCensusData: String = {
    logger.info("inside state wise sorted census data")
    sort(2)
  }
  /***
   * Function calls sort function and tells to perform sorting wrt population
   *
   * @return string value of sorted data
   */
  def getPopulationWiseSortedCensusData: String = {
    logger.info("inside population wise census data")
    sort(3)
  }
  /***
   * Function calls sort function and tells to perform sorting wrt population density
   *
   * @return string value of sorted data
   */
  def getPopulationDensityWiseSortedCensusData: String = {
    logger.info("inside pop density wise census data")
    sort(4)
  }
  /***
   * Function calls sort function and tells to perform sorting wrt largest Area
   *
   * @return string value of sorted data
   */
  def getAreaWiseSortedCensusData: String = {
    logger.info("inside area wise census data")
    sort(5)
  }
}
