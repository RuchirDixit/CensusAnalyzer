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
