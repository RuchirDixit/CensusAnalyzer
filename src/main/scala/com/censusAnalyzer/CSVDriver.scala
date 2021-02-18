
package com.censusAnalyzer

import com.censusAnalyzer.IndiaStateCensusDataAnalyser.{loadIndiaStateCensusData,printStateCensusData,sortStateCensusDataByStateName,sortStateCensusDataByPopulation,table}
import com.censusAnalyzer.IndiaStateCodeAnalyser.{loadIndiaStateCode,printStateCode,sortStateCodeByStateName}
import com.censusAnalyzer.USCensusDataAnalyser.{loadUSCensusData, printUSCensusData, sortUSCensusDataByStateName}
import com.censusAnalyzer.exception.CensusAnalyzerException


object CSVDriver {
  def main(args: Array[String]): Unit = {
    try {
      loadIndiaStateCensusData()
      loadIndiaStateCode()
      loadUSCensusData()

      println("1. Sort by State Name [India-Census Database]\n" +
        "2. Sort by Population [India-Census Database]\n" +
        "3. Sort by State Name [India-State-Code Database]\n" +
        "4. Sort by State Name [US-State-Census Database]");
      try{
        val choice = scala.io.StdIn.readInt()
        choice match {
          case 1 =>
            sortStateCensusDataByStateName()
            printStateCensusData()
          case 2 =>
            sortStateCensusDataByPopulation()
            printStateCensusData()
          case 3 =>
            sortStateCodeByStateName()
            printStateCode()
          case 4 =>
            sortUSCensusDataByStateName()
            printUSCensusData()
        }
      }
    }
    catch{
      case e: CensusAnalyzerException => e.printStackTrace()
    }
  }
}
