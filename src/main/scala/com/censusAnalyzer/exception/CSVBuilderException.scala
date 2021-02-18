package com.censusAnalyzer.exception

class CSVBuilderException(message: CSVBuilderExceptionEnum.Value) extends Exception(message.toString) {}


object CSVBuilderExceptionEnum extends Enumeration {
  type CSVBuilderException = Value
  val PATH_INCORRECT: CSVBuilderExceptionEnum.Value = Value("File Path Incorrect")
  val INCORRECT_FILE: CSVBuilderExceptionEnum.Value = Value("Incorrect File Specified")
  val PARSE_ERROR: CSVBuilderExceptionEnum.Value = Value("Incorrect Delimiters or Fields")
}
