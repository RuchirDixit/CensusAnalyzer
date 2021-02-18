
package com.censusAnalyzer.models

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
   * defines and loads variables wrt IndiaStateCensus class
   * @param indiaCensusCSV reference type for IndiaStateCensus
   */
  def this(indiaCensusCSV: IndiaStateCensus){
    this()
    state=indiaCensusCSV.state
    totalArea=indiaCensusCSV.areaInSqKm
    populationDensity=indiaCensusCSV.densityPerSqKm
    population=indiaCensusCSV.population
  }
  /***
   * defines and loads variables wrt IndiaStateCode class
   * @param indiaStateCodeCSV reference type for IndiaStateCode
   */
  def this(indiaStateCodeCSV: IndiaStateCode){
    this()
    state=indiaStateCodeCSV.state
    stateCode= indiaStateCodeCSV.stateCode
  }
  /***
   * defines and loads variables wrt USCensusData class
   * @param usCensusCSV reference type for USCensusData
   */
  def this(usCensusCSV:USCensusData){
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
