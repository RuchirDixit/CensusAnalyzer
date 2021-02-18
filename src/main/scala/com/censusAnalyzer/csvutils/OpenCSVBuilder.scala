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
package com.censusAnalyzer.csvutils

import java.io.Reader
import java.util
import com.censusAnalyzer.exception.{CSVBuilderException, CSVBuilderExceptionEnum}
import com.opencsv.bean.{CsvToBean, CsvToBeanBuilder}

// OpenCSVBuilder to get CSV Bean, fetch list and get Iterator
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
