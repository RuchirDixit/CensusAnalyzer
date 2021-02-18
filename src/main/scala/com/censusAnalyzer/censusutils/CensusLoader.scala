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
package com.censusAnalyzer.censusutils

import java.nio.file.{Files, NoSuchFileException, Paths}
import java.util
import com.censusAnalyzer.exception.CensusAnalyzerException
import com.censusAnalyzer.exception.CensusAnalyzerException.Issue
import scala.io.Source

object CensusLoader {
  /**
   * To Load CSV data
   * @param filePath : Path of the file
   * @param headers : Header passed
   * @throws CensusAnalyzerException : when required thrown
   * @return : util.ArrayList[util.ArrayList[String]]
   */
  @throws[CensusAnalyzerException]
  @Deprecated
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
        for (stringAdd <- column) {
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
      case e => throw e
    }
  }

  /**
   *  To chec file properties
   * @param filePath : Path of the file passed
   * @param headers : Header passed
   */
  def checkFileProperties(filePath: String, headers: Array[String]): Unit = {
    if (!filePath.endsWith(".csv")) {
      throw new CensusAnalyzerException(Issue.INCORRECT_FILE)
    }
    try {
      val fileReader = Files.newBufferedReader(Paths.get(filePath))
      val line: String = fileReader.readLine()

      val column = line.split(",").map(_.trim)
      if (column.length != headers.length) {
        throw new CensusAnalyzerException(Issue.INVALID_DELIMITER)
      }
      for (headerIndex <- headers.indices)
        if (column(headerIndex).toLowerCase != headers(headerIndex).toLowerCase()) {
          throw new CensusAnalyzerException(Issue.INVALID_FIELDS)
        }

      fileReader.close()
    }
    catch{
      case _:NoSuchFileException => throw new CensusAnalyzerException(Issue.PATH_INCORRECT)
    }
  }
}
