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

  /**
   * To get data
   * @param column : index
   * @return ; Object
   */
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
