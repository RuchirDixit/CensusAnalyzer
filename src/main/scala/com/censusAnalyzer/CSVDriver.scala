
package com.censusAnalyzer

import com.censusAnalyzer.IndiaStateCensusDataAnalyser.{loadIndiaStateCensusData,printStateCensusData,sortStateCensusDataByStateName,table}
import com.censusAnalyzer.IndiaStateCodeAnalyser.{loadIndiaStateCode,printStateCode,sortStateCodeByStateName}
import com.censusAnalyzer.exception.CensusAnalyzerException


object CSVDriver {
  def main(args: Array[String]): Unit = {
    try {
      loadIndiaStateCensusData()
      loadIndiaStateCode()

      sortStateCensusDataByStateName()
      printStateCensusData()

      sortStateCodeByStateName()
      printStateCode()
    }
    catch{
      case e: CensusAnalyzerException => e.printStackTrace()
    }
  }
}
