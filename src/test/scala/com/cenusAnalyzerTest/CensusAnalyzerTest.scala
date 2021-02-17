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
package com.cenusAnalyzerTest

import com.censusAnalyzer.CensusAnalyzer
import com.censusAnalyzer.exception.CensusAnalyzerException
import com.censusAnalyzer.exception.CensusAnalyzerException.Issue
import org.scalatest.FunSuite

/***
 * Test Use case Class which extends FunSuite scala test class
 * Added ScalaTest dependency in Build.sbt
 * Uses Gson and CSV dependencies
 */

class CensusAnalyserTest extends FunSuite {
  val indiaStateCensusDataPath = "C:\\Users\\Ruchir Dixit\\IdeaProjects\\Census_Analyzer\\src\\test\\Resources\\IndiaStateCensusData.csv"
  val wrongFilePathForIndiaStateCensusData = "./IndiaStateCensusData.csv"
  val wrongTypeOfIndiaStateCensusData = "C:\\Users\\Ruchir Dixit\\IdeaProjects\\Census_Analyzer\\src\\test\\Resources\\IndiaStateCensusData.txt"
  val wrongHeaderIndiaStateCensusPath = "C:\\Users\\Ruchir Dixit\\IdeaProjects\\Census_Analyzer\\src\\test\\Resources\\IndiaStateCodeWrongHeader.csv"
  val CensusAnalyzerObj = new CensusAnalyzer()

  test("givenIndianCensusCSVFileShouldReturnCorrectNumberOfRecords") {
    assert(CensusAnalyzerObj.loadCSVData(indiaStateCensusDataPath) === 29)
  }

  test("givenIndianCensusDataCSVFileIfWrongFilePathShouldThrowException") {
    val thrown = intercept[CensusAnalyzerException] {
      CensusAnalyzerObj.loadCSVData(wrongFilePathForIndiaStateCensusData)
    }
    assert(thrown.getMessage === Issue.PATH_INCORRECT)
  }
  test("givenIndianCensusDataFileIfWrongTypeShouldThrowException") {
    val thrown = intercept[Exception] {
      CensusAnalyzerObj.loadCSVData(wrongTypeOfIndiaStateCensusData)
    }
    assert(thrown.getMessage === Issue.INCORRECT_FILE.toString)
  }
  test("givenIndianCensusDataFileIfWrongHeaderShouldThrowException") {
    val thrown = intercept[Exception] {
      CensusAnalyzerObj.loadCSVData(wrongHeaderIndiaStateCensusPath)
    }
    assert(thrown.getMessage === Issue.INVALID_FIELDS.toString)
  }
}
