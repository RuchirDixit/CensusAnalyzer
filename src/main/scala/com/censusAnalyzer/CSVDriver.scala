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
package com.censusAnalyzer

import com.censusAnalyzer.censusutils.IndiaStateCensusDataAnalyser.{loadIndiaStateCensusData,
  printStateCensusData, sortStateCensusDataByArea, sortStateCensusDataByPopulation, sortStateCensusDataByStateName}
import com.censusAnalyzer.censusutils.IndiaStateCodeAnalyser.{loadIndiaStateCode, printStateCode, sortStateCodeByStateName}
import com.censusAnalyzer.censusutils.USCensusDataAnalyser.{loadUSCensusData, printUSCensusData, sortUSCensusDataByPopulation, sortUSCensusDataByStateName}
import com.censusAnalyzer.exception.CensusAnalyzerException
import com.typesafe.scalalogging.LazyLogging


object CSVDriver extends LazyLogging{
  def main(args: Array[String]): Unit = {
    try {
      loadIndiaStateCensusData()
      loadIndiaStateCode()
      loadUSCensusData()

      logger.info("1. Sort by State Name [India-Census Database]\n2." +
        "Sort by State Size [India-Census Database]\n3." +
        "Sort by Population [India-Census Database]\n4. Sort by State Name [India-State-Code Database]\n5." +
        "Sort by State Name [US-State-Census Database]\n6. Sort by Population [US-State-Census Database]");
      try{
        val choice = scala.io.StdIn.readInt()
        choice match {
          case 1 =>
            sortStateCensusDataByStateName()
            printStateCensusData()
          case 2 =>
            sortStateCensusDataByArea()
            printStateCensusData()
          case 3 =>
            sortStateCensusDataByPopulation()
            printStateCensusData()
          case 4 =>
            sortStateCodeByStateName()
            printStateCode()
          case 5 =>
            sortUSCensusDataByStateName()
            printUSCensusData()
          case 6 =>
            sortUSCensusDataByPopulation()
            printUSCensusData()
        }
      }
    }
    catch{
      case e: CensusAnalyzerException => e.printStackTrace()
    }
  }
}
