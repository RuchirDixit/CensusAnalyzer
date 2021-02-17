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
package com.censusAnalyzer.censusanalyzer

import com.censusAnalyzer.statecode.IndiaStateCodeDTO

/***
 * Data Access Object class
 */
class CensusDAO {
  var state:String = _
  var population:Double = 0
  var totalArea :Double= 0
  var populationDensity :Double= 0
  var stateCode:String = _
  var housingUnits:Double = 0
  var waterArea:Double = 0
  var landArea:Double = 0
  var housingDensity:Double = 0

  /***
   * defines and loads variables wrt IndiaCensusDTO class
   * @param indiaCensusCSV reference type for IndiaCensusDTO
   */
  def this(indiaCensusCSV: IndiaCensusDTO){
    this()
    state=indiaCensusCSV.state
    totalArea=indiaCensusCSV.areaInSqKm
    populationDensity=indiaCensusCSV.densityPerSqKm
    population=indiaCensusCSV.population
  }
  /***
   * defines and loads variables wrt IndiaStateCodeCensusDTO class
   * @param indiaStateCodeCSV reference type for IndiaStateCodeDTO
   */
  def this(indiaStateCodeCSV: IndiaStateCodeDTO){
    this()
    state=indiaStateCodeCSV.stateName
    stateCode= indiaStateCodeCSV.stateCode
  }
  /***
   * defines and loads variables wrt USCensusDTO class
   * @param usCensusCSV reference type for USCensusDTO
   */
  def this(usCensusCSV:USCensusDTO){
    this()
    stateCode = usCensusCSV.stateId
    state = usCensusCSV.state
    totalArea = usCensusCSV.totalArea
    populationDensity = usCensusCSV.populationDensity
    population = usCensusCSV.population
    housingDensity = usCensusCSV.housingDensity
    waterArea = usCensusCSV.waterArea
    landArea = usCensusCSV.landArea
    housingUnits=usCensusCSV.housingUnits
  }
}
