
package com.censusAnalyzer

import com.censusAnalyzer.exception.CensusAnalyzerException
import com.censusAnalyzer.exception.CensusAnalyzerException.Issue

class CensusAnalyzer {
  def loadCSVData(filePath: String): Int = {
    try {
      if(!filePath.endsWith(".csv")) {
        throw new CensusAnalyzerException(Issue.INCORRECT_FILE)
      }
      val FileReader = io.Source.fromFile(filePath)
      var rowsCounted = 0
      for(line <- FileReader.getLines()) {
        val column = line.split(",").map(_.trim)
        if(column.length != 4) {
          throw new CensusAnalyzerException(Issue.INVALID_DELIMITER)
        }
        if(rowsCounted == 0){
          if(column(0).toLowerCase != "state" || column(1).toLowerCase != "population" ||
            column(2).toLowerCase != "areainsqkm" || column(3).toLowerCase != "densitypersqkm" ) {
            throw new CensusAnalyzerException(Issue.INVALID_FIELDS)
          }
        }
        rowsCounted += 1
      }
      FileReader.close()
      rowsCounted - 1
    }
    catch {
      case _:java.io.FileNotFoundException =>
        throw new CensusAnalyzerException(Issue.PATH_INCORRECT)
    }
  }
}
