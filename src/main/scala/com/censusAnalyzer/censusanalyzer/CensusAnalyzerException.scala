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

/***
 * Custom Exception class which uses Enum types
 *
 * @param message
 */

class CensusAnalyserException(message: CensusAnalyzerExceptionEnums.Value) extends Exception(message.toString) {}

object CensusAnalyzerExceptionEnums extends Enumeration {
  type CensusAnalyserException = Value

  val InCorrectFilePath: CensusAnalyzerExceptionEnums.Value = Value("Incorrect File Path provided")
  val InCorrectFileType: CensusAnalyzerExceptionEnums.Value = Value("Incorrect File Type provided")
  val UnableToParse: CensusAnalyzerExceptionEnums.Value = Value("Not able to Parse Invalid Delimiter or Fields")
  val NoCensusData: CensusAnalyzerExceptionEnums.Value = Value("Not Data available")
  val InvalidCountry: CensusAnalyzerExceptionEnums.Value = Value("The Country name is Invalid")
}
