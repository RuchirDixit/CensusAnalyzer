package com.censusAnalyzer.models

import com.opencsv.bean.CsvBindByName

class IndiaStateCode {
  @CsvBindByName(column = "SrNo", required = true) var srNo: String = _
  @CsvBindByName(column = "State Name", required = true) var state: String = _
  @CsvBindByName(column = "TIN", required = true) var tin: Int = 0
  @CsvBindByName(column = "StateCode", required = true) var stateCode: String = _

  override def toString: String = "StateCode{" + "SrNo='" + srNo + '\'' + ", state='" + state + '\'' + ", TIN='" + tin + '\'' + ", stateCode='" + stateCode + '\'' + '}'

  def get(column: Int):Object = {
    column match{
      case 0 => srNo.asInstanceOf[Object]
      case 1 => state.asInstanceOf[Object]
      case 2 => tin.asInstanceOf[Object]
      case 3 => stateCode.asInstanceOf[Object]
    }
  }
}
