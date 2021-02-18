package com.censusAnalyzer

object CSVBuilderFactory {
  def createCSVBuilder(): CSVBuilderTrait = {
    new OpenCSVBuilder()
  }
}
