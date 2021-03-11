/**
  * Copyright (C) 2003-2021, e-Evolution Consultants S.A. , http://www.e-evolution.com
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  * You should have received a copy of the GNU General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
  * Created by victor.perez@e-evolution.com , www.e-evolution.com
  */
package org.eevolution.context.workflow.infrastructure.service

import com.google.protobuf.ByteString
import datatype.application_dictionary_data_type.{
  Binary,
  CostPrice,
  Date,
  DateTime,
  ID,
  Integer,
  Number,
  Quantity,
  Search,
  String,
  Table,
  TableDirect,
  YesNo
}
import org.eevolution.context.kernel.domain.ubiquitouslanguage

import java.time.{LocalDateTime, ZoneOffset}

/**
  * Mapper for gRPC Dictionary Application Messages to Scala Dictionary Application Type
  */
package object grpc {

  /**
    * Mapper Domain CostPrice to gRPC maybe CostPrice
    * @param costPrice CostPrice Type
    * @return gRPC maybe CostPrice
    */
  implicit def costPriceToMaybeGrpcCostPrice(
      costPrice: ubiquitouslanguage.CostPrice
  ): Option[CostPrice] = {
    Option(CostPrice(costPrice.toString(), costPrice.scale))
  }

  /**
    * Mapper Domain DateTime to gRPC maybe DateTime
    * @param dateTime DateTime Type
    * @return gRPC maybe DateTime
    */
  implicit def dateTimeToMaybeGrpcDateTime(
      dateTime: ubiquitouslanguage.DateTime
  ): Option[DateTime] = {
    Option(DateTime(dateTime.getSecond, dateTime.getNano))
  }

  /**
    * Mapper Domain maybe DateTime to gRPC maybe DateTime
    * @param maybeDateTime DateTime Type
    * @return gRPC maybe DateTime
    */
  implicit def maybeDateTimeToMaybeGrpcDateTime(
      maybeDateTime: Option[
        ubiquitouslanguage.DateTime
      ]
  ): Option[DateTime] = {
    maybeDateTime match {
      case Some(value) => Option(DateTime(value.getSecond, value.getNano))
      case None        => Option.empty
    }
  }

  /**
    * Mapper gRPC maybe DateTime to Domain maybe DateTime
    * @param maybeDateTime DateTime Type
    * @return gRPC maybe DateTime
    */
  implicit def maybeGrpcDateTimeToMaybeDateTime(
      maybeDateTime: Option[
        DateTime
      ]
  ): Option[
    ubiquitouslanguage.DateTime
  ] = {
    maybeDateTime match {
      case Some(value) =>
        Option(
          LocalDateTime.ofEpochSecond(
            value.seconds,
            value.nanos,
            ZoneOffset.UTC
          )
        )
      case None => Option.empty
    }
  }

  /**
    * Mapper gRPC maybe DateTime to Domain DateTime
    * @param maybeDateTime DateTime Type
    * @return Domain DateTime
    */
  implicit def maybeGrpcDateTimeToDateTime(
      maybeDateTime: Option[
        DateTime
      ]
  ): ubiquitouslanguage.DateTime = {
    maybeDateTime match {
      case Some(value) =>
        LocalDateTime.ofEpochSecond(
          value.seconds,
          value.nanos,
          ZoneOffset.UTC
        )
      case None => LocalDateTime.now()
    }
  }

  /**
    * Mapper Domain maybe DateTime  to gRPC maybe Date
    * @param maybeDateTime DateTime Type
    * @return gRPC maybe Date
    */
  implicit def maybeDateTimeToMaybeGrpcDate(
      maybeDateTime: Option[
        ubiquitouslanguage.DateTime
      ]
  ): Option[Date] = {
    maybeDateTime match {
      case Some(value) => Option(Date(value.getSecond, value.getNano))
      case None        => Option.empty
    }
  }

  /**
    * Mapper gRPC maybe Date to Domain maybe DateTime
    * @param maybeDate DateTime Type
    * @return Domain maybe DateTime
    */
  implicit def maybeGrpcDateToMaybeDate(maybeDate: Option[Date]): Option[
    ubiquitouslanguage.DateTime
  ] = {
    maybeDate match {
      case Some(value) =>
        Option(
          LocalDateTime.ofEpochSecond(
            value.seconds,
            value.nanos,
            ZoneOffset.UTC
          )
        )
      case None => Option.empty
    }
  }

  /**
    * Mapper Domain maybe Number to gRPC maybe Number
    * @param maybeNumber Number Type
    * @return Domain maybe DateTime
    */
  implicit def maybeNumberToMaybeGrpcNumber(
      maybeNumber: Option[
        ubiquitouslanguage.Number
      ]
  ): Option[Number] = {
    maybeNumber match {
      case Some(number) => Option(Number(number.toString(), number.scale))
      case None         => Option(Number("0", 0))
    }
  }

  /**
    * Mapper Domain Number to gRPC maybe Number
    * @param number Number Type
    * @return Domain maybe DateTime
    */
  implicit def numberToMaybeGrpcNumber(
      number: ubiquitouslanguage.Number
  ): Option[Number] = {
    Option(Number(number.toString(), number.scale))
  }

  /**
    * Mapper gRPC maybe Number  to Domain maybe Number
    * @param maybeNumber Number Type
    * @return Domain maybe Number
    */
  implicit def maybeGrpcNumberToMaybeNumber(
      maybeNumber: Option[Number]
  ): Option[ubiquitouslanguage.Number] = {
    maybeNumber match {
      case Some(number) =>
        Option(BigDecimal(number.value).setScale(number.scale))
      case None => Option(BigDecimal(0))
    }
  }

  /**
    * Mapper gRPC maybe Number  to Domain Number
    * @param maybeNumber Number Type
    * @return to Domain  Number
    */
  implicit def maybeGrpcNumberToNumber(
      maybeNumber: Option[Number]
  ): ubiquitouslanguage.Number = {
    maybeNumber match {
      case Some(number) =>
        BigDecimal(number.value).setScale(number.scale)
      case None => BigDecimal(0)
    }
  }

  /**
    * Mapper Domain maybe Quantity to gRPC maybe Quantity
    * @param maybeQuantity Quantity Type
    * @return gRPC maybe Quantity
    */
  implicit def maybeQuantityToMaybeGrpcQuantity(
      maybeQuantity: Option[
        ubiquitouslanguage.Quantity
      ]
  ): Option[Quantity] = {
    maybeQuantity match {
      case Some(quantity) =>
        Option(Quantity(quantity.toString(), quantity.scale))
      case None => Option(Quantity("0", 0))
    }
  }

  /**
    * Mapper gRPC maybe Quantity  to Domain maybe Quantity
    * @param maybeQuantity Quantity Type
    * @return Domain maybe Quantity
    */
  implicit def maybeGrpcQuantityToMaybeQuantity(
      maybeQuantity: Option[Quantity]
  ): Option[
    ubiquitouslanguage.Quantity
  ] = {
    maybeQuantity match {
      case Some(quantity) =>
        Option(BigDecimal(quantity.value).setScale(quantity.scale))
      case None => Option(BigDecimal(0))
    }
  }

  /**
    * Mapper gRPC maybe CostPrice  to Domain maybe CostPrice
    * @param maybeCostPrice CostPrice Type
    * @return gRPC maybe CostPrice
    */
  implicit def maybeGrpcCostPriceToCostPrice(
      maybeCostPrice: Option[CostPrice]
  ): ubiquitouslanguage.CostPrice = {
    maybeCostPrice match {
      case Some(costPrice) =>
        BigDecimal(costPrice.value).setScale(costPrice.scale)
      case None => BigDecimal(0)
    }
  }

  /**
    * Mapper gRPC maybe TableDir  to Domain maybe TableDir
    * @param maybeTableDirect TableDir Type
    * @return gRPC maybe TableDir
    */
  implicit def maybeGrpcTableDirToMaybeTableDir(
      maybeTableDirect: Option[TableDirect]
  ): Option[ubiquitouslanguage.TableDirect] = {
    maybeTableDirect match {
      case Some(tableDirect) =>
        Option(tableDirect.value)
      case None => Option(0)
    }
  }

  /**
    * Mapper Domain maybe Int to gRPC maybe Integer
    * @param maybeInt Number Type
    * @return gRPC maybe Number
    */
  implicit def maybeIntToMaybeGrpcInteger(
      maybeInt: Option[Int]
  ): Option[Integer] = {
    maybeInt match {
      case Some(value) =>
        Option(Integer(value))
      case None => Option(Integer(0))
    }
  }

  /**
    * Mapper  gRPC maybe Integer to Domain Int
    * @param maybeInt Integer Type
    * @return  Domain Int
    */
  implicit def maybeGrpcIntegerToInt(
      maybeInt: Option[Integer]
  ): Int = {
    maybeInt match {
      case Some(integer) =>
        integer.value
      case None => 0
    }
  }

  /**
    * Mapper  gRPC maybe Integer to maybe Domain Int
    * @param maybeInt Integer Type
    * @return  maybe Domain Int
    */
  implicit def maybeGrpcIntegerToMaybeInt(
      maybeInt: Option[Integer]
  ): Option[Int] = {
    maybeInt match {
      case Some(integer) =>
        Option(integer.value)
      case None => Option(0)
    }
  }

  /**
    * Mapper Domain Int to gRPC maybe Integer
    * @param integer Int Type
    * @return gRPC maybe Integer
    */
  implicit def intToMaybeGrpcInteger(
      integer: Int
  ): Option[Integer] = {
    Option(Integer(integer))
  }

  /**
    * Mapper Domain maybe Int to gRPC maybe ID
    * @param maybeInt Number Type
    * @return gRPC maybe ID
    */
  implicit def maybeIntToMaybeGrpcId(
      maybeInt: Option[Int]
  ): Option[ID] = {
    maybeInt match {
      case Some(value) =>
        Option(ID(value))
      case None => Option(ID(0))
    }
  }

  /**
    * Mapper Domain Int to gRPC maybe Integer
    * @param integer Int Type
    * @return gRPC maybe Integer
    */
  implicit def intToMaybeGrpcID(
      integer: Int
  ): Option[ID] = {
    Option(ID(integer))
  }

  /**
    * Mapper Domain Int to gRPC maybe Integer
    * @param maybeInteger Int Type
    * @return gRPC maybe Integer
    */
  implicit def maybeGrpcIDToInt(
      maybeInteger: Option[ID]
  ): Int = {
    maybeInteger match {
      case Some(id) => id.value
      case None     => 0
    }
  }

  /**
    * Mapper Domain maybe Search to gRPC maybe Search
    * @param maybeSearch Search Type
    * @return gRPC maybe Search
    */
  implicit def maybeIntToMaybeGrpcSearch(
      maybeSearch: Option[Int]
  ): Option[Search] = {
    maybeSearch match {
      case Some(value) =>
        Option(Search(value))
      case None => Option(Search(0))
    }
  }

  /**
    * Mapper gRPC maybe Search to Domain maybe Search
    * @param maybeSearch Search Type
    * @return Domain maybe Search
    */
  implicit def maybeGrpcSearchToMaybeSearch(
      maybeSearch: Option[Search]
  ): Option[Int] = {
    maybeSearch match {
      case Some(search) =>
        Option(search.value)
      case None => Option(0)
    }
  }

  /**
    * Mapper gRPC maybe Search to Domain  Search
    * @param maybeSearch Search Type
    * @return Domain  Search
    */
  implicit def maybeGrpcSearchToSearch(
      maybeSearch: Option[Search]
  ): Int = {
    maybeSearch match {
      case Some(search) =>
        search.value
      case None => 0
    }
  }

  /**
    * Mapper Domain Search to gRPC maybe Search
    * @param search Int Type
    * @return gRPC maybe Search
    */
  implicit def intToMaybeGrpcSearch(
      search: Int
  ): Option[Search] = {
    Option(Search(search))
  }

  /**
    * Mapper gRPC maybe Number  to Domain TableDirect
    * @param maybeTableDirect Number Type
    * @return gRPC maybe Number
    */
  implicit def maybeGrpcTableDirToTableDir(
      maybeTableDirect: Option[TableDirect]
  ): ubiquitouslanguage.TableDirect = {
    maybeTableDirect match {
      case Some(tableDirect) =>
        tableDirect.value
      case None => 0
    }
  }

  /**
    * Mapper gRPC maybe Number  to Domain maybe Number
    * @param maybeTableDirect Number Type
    * @return gRPC maybe Number
    */
  implicit def maybeTableDirToMaybeGrpcTableDir(
      maybeTableDirect: Option[ubiquitouslanguage.TableDirect]
  ): Option[TableDirect] = {
    maybeTableDirect match {
      case Some(tableDirect) =>
        Option(TableDirect(tableDirect))
      case None => Option(TableDirect(0))
    }
  }

  /**
    * Mapper Domain TableDirect to gRPC maybe TableDirect
    * @param tableDirect Number Type
    * @return gRPC maybe TableDirect
    */
  implicit def tableDirToMaybeGrpcTableDir(
      tableDirect: ubiquitouslanguage.TableDirect
  ): Option[TableDirect] = {
    Option(TableDirect(tableDirect))
  }

  /**
    * Mapper Domain Table to  gRPC maybe Table
    * @param table Number Type
    * @return gRPC maybe TableDirect
    */
  implicit def tableToMaybeGrpcTable(
      table: ubiquitouslanguage.Table
  ): Option[Table] = {
    Option(Table(table))
  }

  /**
    * Mapper gRPC maybe Number  to Domain TableDirect
    * @param maybeTable Number Type
    * @return gRPC maybe Number
    */
  implicit def maybeGrpcTableToTable(
      maybeTable: Option[Table]
  ): ubiquitouslanguage.Table = {
    maybeTable match {
      case Some(table) =>
        table.value
      case None => 0
    }
  }

  /**
    * Mapper gRPC maybe Number  to Domain maybe Number
    * @param maybeTable Number Type
    * @return gRPC maybe Number
    */
  implicit def maybeGrpcTableToMaybeTable(
      maybeTable: Option[Table]
  ): Option[ubiquitouslanguage.Table] = {
    maybeTable match {
      case Some(table) =>
        Option(table.value)
      case None => Option(0)
    }
  }

  /**
    * Mapper gRPC maybe Number  to Domain maybe Number
    * @param maybeTable Number Type
    * @return gRPC maybe Number
    */
  implicit def maybeTableToMaybeGrpcTable(
      maybeTable: Option[ubiquitouslanguage.Table]
  ): Option[Table] = {
    maybeTable match {
      case Some(table) =>
        Option(Table(table))
      case None => Option(Table(0))
    }
  }

  /**
    * Mapper gRPC maybe YesNo  to Domain maybe YesNo
    * @param maybeYesNo YesNo Type
    * @return gRPC maybe YesNo
    */
  implicit def maybeGrpcYesNoToYesNo(
      maybeYesNo: Option[YesNo]
  ): ubiquitouslanguage.YesNo = {
    maybeYesNo match {
      case Some(yesNo) => yesNo.value
      case None        => false
    }
  }

  /**
    * Mapper gRPC maybe YesNo  to Domain maybe YesNo
    * @param maybeYesNo YesNo Type
    * @return gRPC maybe YesNo
    */
  implicit def maybeGrpcYesNoToMaybeYesNo(
      maybeYesNo: Option[YesNo]
  ): Option[ubiquitouslanguage.YesNo] = {
    maybeYesNo match {
      case Some(yesNo) => Option(yesNo.value)
      case None        => Option(false)
    }
  }

  /**
    * Mapper gRPC maybe YesNo  to Domain maybe YesNo
    * @param yesNo Number Type
    * @return gRPC maybe YesNo
    */
  implicit def yesNoToMaybeGrpcYesNo(
      yesNo: Boolean
  ): Option[YesNo] = {
    Option(YesNo(yesNo))
  }

  /**
    * Mapper gRPC maybe YesNo  to Domain maybe YesNo
    * @param maybeYesNo Number Type
    * @return gRPC maybe YesNo
    */
  implicit def yesNoToMaybeGrpcYesNo(
      maybeYesNo: Option[Boolean]
  ): Option[YesNo] = {
    maybeYesNo match {
      case Some(yesNo) => Option(YesNo(yesNo))
      case None        => Option(YesNo(false))
    }

  }

  /**
    * Mapper Domain maybe String to gRPC maybe String
    * @param maybeString Number Type
    * @return Domain String
    */
  implicit def maybeStringToGrpcString(
      maybeString: Option[java.lang.String]
  ): Option[String] = {
    maybeString match {
      case Some(stringValue) => Option(String(stringValue))
      case None              => Option(String(""))
    }
  }

  /**
    * Mapper Domain maybe String to gRPC maybe String
    * @param string String Type
    * @return gRPC maybe String
    */
  implicit def stringToGrpcString(
      string: java.lang.String
  ): Option[String] = {
    val maybeString = Option(string)
    if (maybeString.isDefined)
      Option(String(string))
    else
      Option(String(""))
  }

  /**
    * Mapper gRPC maybe String  to Domain String
    * @param maybeString Number Type
    * @return Domain String
    */
  implicit def maybeGrpcStringToString(
      maybeString: Option[String]
  ): java.lang.String = {
    maybeString match {
      case Some(stringValue) =>
        stringValue.value
      case None => ""
    }
  }

  /**
    * Mapper gRPC maybe String  to Domain maybe String
    * @param maybeString Number Type
    * @return Domain maybe String
    */
  implicit def maybeGrpcStringToMaybeString(
      maybeString: Option[String]
  ): Option[java.lang.String] = {
    maybeString match {
      case Some(stringValue) =>
        Option(stringValue.value)
      case None => Option("")
    }
  }

  /**
    * Mapper gRPC maybe String  to Domain maybe String
    * @param maybeString Number Type
    * @return Domain maybe String
    */
  implicit def maybeGrpcBinaryToMaybeByte(
      maybeBinary: Option[Binary]
  ): Option[Array[Byte]] = {
    maybeBinary match {
      case Some(binary) =>
        Option(binary.value.toByteArray)
      case None => Option.empty
    }
  }

  /**
    * Mapper gRPC maybe String  to Domain maybe String
    * @param maybeBinary Number Type
    * @return Domain maybe String
    */
  implicit def maybeBinaryToMaybeGrpcBinary(
      maybeBinary: Option[Array[Byte]]
  ): Option[Binary] = {
    maybeBinary match {
      case Some(binary) =>
        Option(Binary(ByteString.copyFrom(binary)))
      case None => Option.empty
    }
  }
}
