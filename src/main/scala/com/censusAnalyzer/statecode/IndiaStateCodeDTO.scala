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
package com.censusAnalyzer.statecode

import com.opencsv.bean.CsvBindByName

/***
 * class Binds Data to form Json format
 * Uses OpenCSV dependency which is added to build.sbt
 */
class IndiaStateCodeDTO {


  @CsvBindByName(column = "SrNo", required = true)
  var SrNo: String = _
  @CsvBindByName(column = "State Name", required = true)
  var stateName: String = _
  @CsvBindByName(column = "TIN", required = true)
  var TIN: Int = 0
  @CsvBindByName(column = "StateCode", required = true)
  var stateCode: String = _

  // overrides objects to form string representation of an object
  override def toString: String = "IndiaStateCodeCSV{" +
    "SrNo='" + SrNo + '\'' +
    ", state='" + stateName + '\'' +
    ", TIN='" + TIN + '\'' +
    ", stateCode='" + stateCode + '\'' + '}'
}
