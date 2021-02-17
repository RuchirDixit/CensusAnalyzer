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

import java.nio.file.{Files, NoSuchFileException, Paths}
import java.util

import com.censusAnalyzer.censusanalyzer.Country.Country
import com.censusAnalyzer.statecode.IndiaStateCodeDTO
import com.typesafe.scalalogging.LazyLogging

/***
 * Class Loads Data by calling DAO class
 * Added CSV builder JAR in the project target
 */
  class CensusLoader extends LazyLogging{
  /***
   * Function Loads Data for respective calls
   * Stores Collection Map data structure
   * Implements Iterator to Iterate the collection objects
   * @return Map key value pair type value
   */
    def loadData[A](country: Country, filePaths: Seq[String]): Map[String, CensusDAO] = {
      try {
        logger.info("inside loadData in census loader")
        for (filePath <- filePaths) {
          if (!filePath.endsWith(".csv")) {
            logger.error(CensusAnalyzerExceptionEnums.InCorrectFileType.toString)
            throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.InCorrectFileType)
          }
        }
        var censusMap: Map[String, CensusDAO] = Map()
        val readerStateCensus = Files.newBufferedReader(Paths.get(filePaths.head))
        val csvBuilderStateCensus = CSVBuilderFactory.createCSVBuilder()
        if (country.equals(Country.India)) {
          val censusCSVIteratorStateCensus: util.Iterator[IndiaCensusDTO] = csvBuilderStateCensus.getCSVFileIterator(readerStateCensus, classOf[IndiaCensusDTO])
          censusCSVIteratorStateCensus.forEachRemaining { objDAO => censusMap += (objDAO.state -> new CensusDAO(objDAO)) }
        }
        else if (country.equals(Country.USA)) {
          val USCensusCSVIterator: util.Iterator[USCensusDTO] = csvBuilderStateCensus.getCSVFileIterator(readerStateCensus, classOf[USCensusDTO])
          USCensusCSVIterator.forEachRemaining { objDAO => censusMap += (objDAO.state -> new CensusDAO(objDAO)) }
        }
        else {
          logger.error(CensusAnalyzerExceptionEnums.InvalidCountry.toString)
          throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.InvalidCountry)
        }
        if (filePaths.length == 1) {
          return censusMap
        }
        loadStateCode(censusMap, filePaths(1): String)
      }
      catch {
        case _: NoSuchFileException =>
          logger.error(CensusAnalyzerExceptionEnums.InCorrectFilePath.toString)
          throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.InCorrectFilePath)
        case _: CSVBuilderException =>
          logger.error(CensusAnalyzerExceptionEnums.UnableToParse.toString)
          throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.UnableToParse)
      }
    }

  /***
   * Function Loads Data for StateCode
   * Stores Collection Map data structure
   * uses Iterator to Iterate the collection objects
   * @return Map key value pair type value
   */
    def loadStateCode(censusMap: Map[String, CensusDAO], filePath: String): Map[String, CensusDAO] = {
      try {
        logger.info("inside load state code")
        val readerStateCode = Files.newBufferedReader(Paths.get(filePath))
        val CSVBuilderStateCode = CSVBuilderFactory.createCSVBuilder()
        val censusCSVIteratorStateCode: util.Iterator[IndiaStateCodeDTO] = CSVBuilderStateCode.getCSVFileIterator(readerStateCode, classOf[IndiaStateCodeDTO])
        var censusStateMap: Map[String, CensusDAO] = Map()
        censusCSVIteratorStateCode.forEachRemaining { objDAO => censusStateMap += (objDAO.stateName -> new CensusDAO(objDAO)) }

        for (stateNameCensus <- censusMap.keys; stateName <- censusStateMap.keys; if (stateName.equals(stateNameCensus))) {
          val censusData = censusMap(stateNameCensus)
          censusData.stateCode = censusStateMap(stateName).stateCode
        }
        censusMap
      }
      catch {
        case _: NoSuchFileException =>
          logger.error(CensusAnalyzerExceptionEnums.InCorrectFilePath.toString)
          throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.InCorrectFilePath)
        case _: CSVBuilderException =>
          logger.error(CensusAnalyzerExceptionEnums.UnableToParse.toString)
          throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.UnableToParse)
      }
    }
  }
