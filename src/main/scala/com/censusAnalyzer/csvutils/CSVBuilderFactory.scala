
package com.censusAnalyzer.csvutils

object CSVBuilderFactory {
  def createCSVBuilder(): CSVBuilderTrait = {
    new OpenCSVBuilder()
  }
}
