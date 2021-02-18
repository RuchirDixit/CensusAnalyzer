package com.censusAnalyzer.models

import com.opencsv.bean.CsvBindByName

/**
 * Deserializes IndiaStateCensusData to their respective fields
 * toString method to convert them into json format
 */
class IndiaStateCensus {
  @CsvBindByName(column = "State", required = true) var state: String = _
  @CsvBindByName(column = "Population", required = true) var population = 0
  @CsvBindByName(column = "AreaInSqKm", required = true) var areaInSqKm = 0
  @CsvBindByName(column = "DensityPerSqKm", required = true) var densityPerSqKm = 0

  override def toString: String = "IndiaCensusCSV{" +
    "State='" + state + '\'' +
    ", Population='" + population + '\'' +
    ", AreaInSqKm='" + areaInSqKm + '\'' +
    ", DensityPerSqKm='" + densityPerSqKm + '\'' + '}'

  def get(column: Int):Object = {
    column match{
      case 0 => state.asInstanceOf[Object]
      case 1 => population.asInstanceOf[Object]
      case 2 => areaInSqKm.asInstanceOf[Object]
      case 3 => densityPerSqKm.asInstanceOf[Object]
    }
  }
}
