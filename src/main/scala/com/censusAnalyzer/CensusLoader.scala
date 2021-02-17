
package com.censusAnalyzer

import java.util
import com.censusAnalyzer.exception.CensusAnalyzerException
import com.censusAnalyzer.exception.CensusAnalyzerException.Issue

import scala.io.Source

/**
 * Created on 11/30/2020.
 * Class: CensusLoader.scala
 * Author: Rajat G.L.
 */
object CensusAnalyzer {
  @throws[CensusAnalyzerException]
  def loadCSVData(filePath: String, headers: Array[String]): util.ArrayList[util.ArrayList[String]] = {
    try {
      val table = new util.ArrayList[util.ArrayList[String]]()
      if (!filePath.endsWith(".csv")) {
        throw new CensusAnalyzerException(Issue.INCORRECT_FILE)
      }
      val FileReader = Source.fromFile(filePath)
      var rowsCounted = 0
      for (line <- FileReader.getLines()) {
        val column = line.split(",").map(_.trim)
        if (column.length != headers.length) {
          throw new CensusAnalyzerException(Issue.INVALID_DELIMITER)
        }
        if (rowsCounted == 0) {
          for (headerIndex <- headers.indices)
            if (column(headerIndex).toLowerCase != headers(headerIndex)) {
              throw new CensusAnalyzerException(Issue.INVALID_FIELDS)
            }
        }
        val colsArray: util.ArrayList[String] = new util.ArrayList()
        for(stringAdd <- column){
          colsArray.add(stringAdd)
        }
        table.add(colsArray)
        rowsCounted += 1
      }
      FileReader.close()
      table
    }
    catch {
      case _: java.io.FileNotFoundException =>
        throw new CensusAnalyzerException(Issue.PATH_INCORRECT)
      case e =>throw e
    }
  }
}
