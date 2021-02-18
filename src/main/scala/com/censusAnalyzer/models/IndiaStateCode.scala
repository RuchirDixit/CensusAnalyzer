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

class IndiaStateCode {
  @CsvBindByName(column = "SrNo", required = true) var srNo: String = _
  @CsvBindByName(column = "State Name", required = true) var state: String = _
  @CsvBindByName(column = "TIN", required = true) var tin: Int = 0
  @CsvBindByName(column = "StateCode", required = true) var stateCode: String = _

  override def toString: String = "StateCode{" +
    "SrNo='" + srNo + '\'' + ", state='" + state + '\'' + ", TIN='" + tin + '\'' + ", stateCode='" + stateCode + '\'' + '}'

  /**
   * To get data
   * @param column : index
   * @return ; Object
   */
  def get(column: Int):Object = {
    column match{
      case 0 => srNo.asInstanceOf[Object]
      case 1 => state.asInstanceOf[Object]
      case 2 => tin.asInstanceOf[Object]
      case 3 => stateCode.asInstanceOf[Object]
    }
  }
}
