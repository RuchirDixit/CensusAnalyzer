
package com.censusAnalyzer.csvutils

import java.io.Reader
import java.util

import com.censusAnalyzer.exception.{CSVBuilderException, CSVBuilderExceptionEnum}
import com.opencsv.bean.{CsvToBean, CsvToBeanBuilder}

class OpenCSVBuilder extends CSVBuilderTrait {
  override def getIterator[T](reader: Reader, csvClass: Class[T]): util.Iterator[T] = {
    try {
      val csvToBean = getCSVBean(reader, csvClass)
      csvToBean.iterator()
    }
    catch {
      case _: java.lang.RuntimeException => throw new CSVBuilderException(CSVBuilderExceptionEnum.PARSE_ERROR)
    }
  }
  def getCSVBean[T](reader: Reader, csvClass: Class[T]): CsvToBean[T] =  {
    try {
      val csvToBeanBuilder = new CsvToBeanBuilder[T](reader)
      csvToBeanBuilder.withType(csvClass)
      csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true)
      val csvToBean = csvToBeanBuilder.build()
      csvToBean
    }
    catch {
      case _:java.lang.RuntimeException => throw new CSVBuilderException(CSVBuilderExceptionEnum.PARSE_ERROR)
    }
  }

  override def fetchList[T](reader: Reader, csvClass: Class[T]): util.List[T] = {
    try {
      val csvToBean = getCSVBean(reader, csvClass)
      csvToBean.parse()
    }
    catch {
      case _: java.lang.RuntimeException => throw new CSVBuilderException(CSVBuilderExceptionEnum.PARSE_ERROR)
    }
  }
}