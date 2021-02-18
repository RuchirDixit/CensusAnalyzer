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

import com.censusAnalyzer.IndiaStateCensusDataAnalyser.loadIndiaStateCensusData
import com.censusAnalyzer.IndiaStateCodeAnalyser.loadIndiaStateCode
import com.censusAnalyzer.exception.CensusAnalyzerException
import com.censusAnalyzer.exception.CensusAnalyzerException.Issue
import org.scalatest.FunSuite

/***
 * Test Use case Class which extends FunSuite scala test class
 * Added ScalaTest dependency in Build.sbt
 * Uses Gson and CSV dependencies
 */

class CensusAnalyserTest extends FunSuite {
  //path variables for IndiaStateCensusData
  val indiaStateCensusDataPath = "asset/IndiaStateCensusData.csv"
  val wrongFilePathForIndiaStateCensusData = "./IndiaStateCensusData.csv"
  val wrongTypeOfIndiaStateCensusData = "asset/IndiaStateCensusData.pdf"
  val wrongHeaderIndiaStateCensusPath = "asset/IndiaStateCensusDataWrongHeader.csv"
  //path variables for IndiaStateCode
  val indiaStateCodeDataPath = "asset/IndiaStateCode.csv"
  val wrongIndiaStateCodeFilePath = "./IndiaStateCode.csv"
  val wrongFileTypeIndiaStateCodePath = "asset/IndiaStateCode.pdf"
  val wrongHeaderIndiaStateCodePath = "asset/IndiaStateCodeWrongHeader.csv"

  test("givenIndianCensusCSVFileShouldReturnCorrectNumberOfRecords") {
    assert(loadIndiaStateCensusData(indiaStateCensusDataPath) === 29)
  }
  test("givenIndianCensusDataCSVFileIfWrongFilePathShouldThrowException") {
    val thrown = intercept[CensusAnalyzerException] {
      loadIndiaStateCensusData(wrongFilePathForIndiaStateCensusData)
    }
    assert(thrown.getMessage === Issue.PATH_INCORRECT)
  }
  test("givenIndianCensusDataFileIfWrongTypeShouldThrowException") {
    val thrown = intercept[CensusAnalyzerException] {
      loadIndiaStateCensusData(wrongTypeOfIndiaStateCensusData)
    }
    assert(thrown.getMessage === Issue.INCORRECT_FILE)
  }
  test("givenIndianCensusDataFileIfWrongHeaderShouldThrowException") {
    val thrown = intercept[CensusAnalyzerException] {
      loadIndiaStateCensusData(wrongHeaderIndiaStateCensusPath)
    }
    assert(thrown.getMessage === Issue.INVALID_FIELDS)
  }

  test("givenIndiaStateCodeCSVFileShouldReturnCorrectNumberOfRecords") {
    assert(loadIndiaStateCode(indiaStateCodeDataPath) === 37)
  }
  test("givenIndiaStateCodeCSVFileIfWrongFilePathShouldThrowException") {
    val thrown = intercept[CensusAnalyzerException] {
      loadIndiaStateCode(wrongIndiaStateCodeFilePath)
    }
    assert(thrown.getMessage === Issue.PATH_INCORRECT)
  }
  test("givenIndiaStateCodeFileIfWrongTypeShouldThrowException") {
    val thrown = intercept[CensusAnalyzerException] {
      loadIndiaStateCode(wrongFileTypeIndiaStateCodePath)
    }
    assert(thrown.getMessage === Issue.INCORRECT_FILE)
  }
  test("givenIndiaStateCodeFileIfWrongHeaderShouldThrowException") {
    val thrown = intercept[CensusAnalyzerException] {
      loadIndiaStateCode(wrongHeaderIndiaStateCodePath)
    }
    assert(thrown.getMessage === Issue.INVALID_FIELDS)
  }

}