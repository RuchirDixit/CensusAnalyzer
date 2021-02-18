
package com.censusAnalyzer.models

import com.opencsv.bean.CsvBindByName

class USCensusData {

  @CsvBindByName(column = "State Id", required = true) var stateId: String = _
  @CsvBindByName(column = "State", required = true) var state: String = _
  @CsvBindByName(column = "Population", required = true) var population: Int = 0
  @CsvBindByName(column = "Housing units", required = true) var housingUnits:Int = 0
  @CsvBindByName(column = "Total area", required = true) var totalArea:Double = 0.0d
  @CsvBindByName(column = "Water area", required = true) var waterArea:Double = 0.0d
  @CsvBindByName(column = "Land area", required = true) var landArea:Double = 0.0d
  @CsvBindByName(column = "Population density", required = true) var populationDensity:Double = 0.0d
  @CsvBindByName(column = "Housing density", required = true) var housingDensity:Double = 0.0d

  override def toString: String = {
    "USCensusData{" +
      "StateId='" + stateId +
      "', State='" + state +
      "', Population='" + population +
      "', Housing Units='" + housingUnits +
      "', Total Area='" + totalArea +
      "', Water Area='" + waterArea +
      "', Land Area='" + landArea +
      "', Population Density='" + populationDensity +
      "', Housing Density='" + housingDensity + "'}"
  }


  def get(column: Int):Object = {
    column match{
      case 0 => stateId.asInstanceOf[Object]
      case 1 => state.asInstanceOf[Object]
      case 2 => population.asInstanceOf[Object]
      case 3 => housingUnits.asInstanceOf[Object]
      case 4 => totalArea.asInstanceOf[Object]
      case 5 => waterArea.asInstanceOf[Object]
      case 6 => landArea.asInstanceOf[Object]
      case 7 => populationDensity.asInstanceOf[Object]
      case 8 => housingDensity.asInstanceOf[Object]
    }
  }
}
