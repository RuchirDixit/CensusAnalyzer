
package com.censusAnalyzer

object CSVDriver {
  def main(args: Array[String]): Unit = {
    val indianStateCensusDataPath = "C:\\Users\\Ruchir Dixit\\IdeaProjects\\Census_Analyzer\\src\\test\\Resources\\IndiaStateCensusData.csv"
    val censusAnalyserObject = new CensusAnalyzer()
    censusAnalyserObject.loadCSVData(indianStateCensusDataPath)
  }
}
