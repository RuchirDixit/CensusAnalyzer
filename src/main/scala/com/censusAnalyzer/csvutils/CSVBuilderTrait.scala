package com.censusAnalyzer.csvutils

import java.io.Reader
import java.util

trait CSVBuilderTrait {
  def getIterator[T](reader: Reader, csvClass: Class[T]): util.Iterator[T]
  def fetchList[T](reader: Reader, csvClass: Class[T]): util.List[T]
}
