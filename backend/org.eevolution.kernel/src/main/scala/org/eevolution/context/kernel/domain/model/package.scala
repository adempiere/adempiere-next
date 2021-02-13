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

package org.eevolution.context.kernel.domain

import org.eevolution.context.kernel.domain.ubiquitouslanguage.{DateTime, Table}
import zio.Task

import java.util.UUID

/**
  * Package with API for Domain Model
  */
package object model {

  /**
    * Define Domain Model type
    */
  trait DomainModel extends Product {
    type DomainModel
  }

  /**
    * Define Active Enabled Type
    */
  trait ActiveEnabled {

    type ActiveEnabled

    val isActive: Boolean
  }

  /**
    * Define Tenant Enabled Type
    */
  trait TenantEnabled {

    type TenantEnabled

    val tenant: Tenant

    def getTenant: Tenant = tenant
  }

  /**
    * Define Identifiable Type
    */
  trait Identifiable {

    type Identifiable

    val entityName: String
    val identifier: String

    def Id: Int

    def EntityName: String = entityName

    def Identifier: String = identifier
  }

  /**
    * Define Organization Enabled type
    */
  trait OrganizationEnabled {

    type OrganizationEnabled

    val organization: Organization

    def getOrganization: Organization = organization
  }

  /**
    * Define Traceable Type
    */
  trait Traceable {

    type Traceable

    val updatedBy: Table
    val createdBy: Table
    val created: DateTime
    val updated: DateTime

    def getCreatedBy: Table = createdBy

    def getCreated: DateTime = created

    def getUpdatedBy: Table = updatedBy

    def getUpdated: DateTime = updated

  }

  /**
    * Generic Service trait
    *
    * @tparam DomainModel Domain Model
    * @tparam idType      id Type
    */
  trait Repository[DomainModel, idType] {

    type Repository

    def getById(id: idType): Task[DomainModel]

    def getByUUID(uuid: UUID): Task[DomainModel]

    def getAllByPage(page: Int, pageSize: Int): Task[DomainModel]

    def getAll: Task[List[DomainModel]]
  }

  /**
    * Generic Service trait
    *
    * @tparam DomainModel Domain Model
    * @tparam idType      id Type
    */
  trait Service[DomainModel, idType] {

    type Service

    def getById(id: idType): Object

    def getByUUID(uuid: UUID): Object

    def getAll: Object

    def getAllByPage(page: Option[Int], pageSize: Option[Int]): Object

  }

  /** Display Type and Application Dictionary Reference */
  object DisplayType {

    /** Display Type 10	String */
    val String: Int = 10

    /** Display Type 11	Integer */
    val Integer: Int = 11

    /** Display Type 12	Amount */
    val Amount: Int = 12

    /** Display Type 13	ID */
    val ID: Int = 13

    /** Display Type 14	Text */
    val Text: Int = 14

    /** Display Type 15	Date */
    val Date: Int = 15

    /** Display Type 16	DateTime */
    val DateTime: Int = 16

    /** Display Type 17	List */
    val List: Int = 17

    /** Display Type 18	Table */
    val Table: Int = 18

    /** Display Type 19	TableDir */
    val TableDir: Int = 19

    /** Display Type 20	YN */
    val YesNo: Int = 20

    /** Display Type 21	Location */
    val Location: Int = 21

    /** Display Type 22	Number */
    val Number: Int = 22

    /** Display Type 23	BLOB */
    val Binary: Int = 23

    /** Display Type 24	Time */
    val Time: Int = 24

    /** Display Type 25	Account */
    val Account: Int = 25

    /** Display Type 26	RowID */
    val RowID: Int = 26

    /** Display Type 27	Color */
    val Color: Int = 27

    /** Display Type 28	Button */
    val Button: Int = 28

    /** Display Type 29	Quantity */
    val Quantity: Int = 29

    /** Display Type 30	Search */
    val Search: Int = 30

    /** Display Type 31	Locator */
    val Locator: Int = 31

    /** Display Type 32 Image */
    val Image: Int = 32

    /** Display Type 33 Assignment */
    val Assignment: Int = 33

    /** Display Type 34	Memo */
    val Memo: Int = 34

    /** Display Type 35	PAttribute */
    val PAttribute: Int = 35

    /** Display Type 36	CLOB */
    val TextLong: Int = 36

    /** Display Type 37	CostPrice */
    val CostPrice: Int = 37

    /** Display Type 38	File Path */
    val FilePath: Int = 38

    /** Display Type 39 File Name */
    val FileName: Int = 39

    /** Display Type 40	URL */
    val URL: Int = 40

    /** Display Type 42	PrinterName */
    val PrinterName: Int = 42

    /** Display Type 53370 Chart */
    val Chart: Int = 53370

    /**
      * Get Class for Reference
      *
      * @param reference      Reference
      * @param yesNoAsBoolean Yes No As Boolean
      * @return
      */
    def getClass(reference: Int, yesNoAsBoolean: Boolean): String =
      if (isText(reference) || reference == List) "String"
      else if (isId(reference) || reference == Integer) "String"
      else if (isNumeric(reference)) "BigDecimal"
      else if (isDate(reference)) "DateTime"
      else if (reference == YesNo) if (yesNoAsBoolean) "Boolean" else "String"
      else if (reference == Button) "String"
      else if (isLob(reference)) "Byte"
      else "AnyRef"

    /**
      * Get Type for Reference
      * @param attributeName
      * @param reference
      * @param referenceValueId
      * @param maybeDefault
      * @param isMandatory
      * @return
      */
    def getType(
        attributeName: String,
        reference: Int,
        referenceValueId: Option[Int],
        maybeDefault: Option[String],
        isMandatory: Boolean = false
    ): String = {
      val typeBuffer = new StringBuilder
      if (!isMandatory) {
        typeBuffer.append("Option[")
      }

      if (reference == ID)
        typeBuffer.append("Id")
      if (reference == Number || reference == Integer)
        typeBuffer.append("Number")
      if (reference == Amount)
        typeBuffer.append("Amount")
      if (reference == CostPrice)
        typeBuffer.append("CostPrice")
      if (reference == Quantity)
        typeBuffer.append("Quantity")
      if (reference == TableDir)
        typeBuffer.append("TableDirect")
      if (reference == PAttribute)
        typeBuffer.append("AttributeSetInstance")
      if (reference == Assignment)
        typeBuffer.append("ResourceAssignment")
      if (reference == Table)
        typeBuffer.append("Table")
      if (reference == Account)
        typeBuffer.append("TableDirect")
      if (reference == Search)
        typeBuffer.append("Search")
      if (reference == Date || reference == DateTime)
        typeBuffer.append("DateTime")
      if (reference == YesNo)
        typeBuffer.append("YesNo")
      if (reference == Button)
        typeBuffer.append("String")
      if (reference == List)
        typeBuffer.append("ListType")
      if (isText(reference))
        typeBuffer.append("String")
      if (!isMandatory)
        typeBuffer.append("]")

      maybeDefault match {
        case Some(default)
            if isNumeric(
              reference
            ) && !isMandatory && (default == "0.0" || default == "0" || default == "1") =>
          typeBuffer.append(" = Some(BigDecimal(0))")
        case Some(default)
            if isNumeric(reference) && default == "0.0" || default == "0" =>
          typeBuffer.append(" = BigDecimal(0)")
        case Some(default)
            if isNumeric(reference) && default == "1.0" || default == "1" =>
          typeBuffer.append(" = BigDecimal(1)")
        case Some(default)
            if isDate(reference) && default == "@#Date@" && !isMandatory =>
          typeBuffer.append(" = Option(java.time.LocalDateTime.now)")
        case Some(default) if default == "@#Date@" =>
          typeBuffer.append(" = java.time.LocalDateTime.now")
        case Some(_)
            if reference == DateTime && (Option(attributeName).getOrElse(
              ""
            ) == "Updated"
              || Option(attributeName).getOrElse("") == "Created") =>
          typeBuffer.append(" = java.time.LocalDateTime.now")
        case Some(default)
            if reference == List && default.length > 0 && isMandatory =>
          typeBuffer.append(" = ").append("\"").append(default).append("\"")
        case Some(default)
            if reference == Button && default.length > 0 && isMandatory =>
          typeBuffer.append(" = \"").append(default).append("\"")
        case Some(default)
            if reference == YesNo && default == "Y" && isMandatory =>
          typeBuffer.append(" = true")
        case Some(default)
            if reference == YesNo && default == "N" && isMandatory =>
          typeBuffer.append(" = false")
        case Some(default)
            if reference == List && default.nonEmpty && !isMandatory =>
          typeBuffer
            .append(" = Option(")
            .append("\"")
            .append(default)
            .append("\")")
        case Some(default)
            if reference == Button && default.length > 0 && !isMandatory =>
          typeBuffer.append(" = Option(\"").append(default).append("\")")
        case Some(default)
            if reference == YesNo && default == "Y" && !isMandatory =>
          typeBuffer.append(" = Option(true)")
        case Some(default)
            if reference == YesNo && default == "N" && !isMandatory =>
          typeBuffer.append(" = Option(false)")
        case Some(_) => typeBuffer.append("")
        case None    => typeBuffer.append("")
      }
      typeBuffer.toString()
    }

    /**
      * Get Type for Reference
      * @param attributeName
      * @param reference
      * @param referenceValueId
      * @param maybeDefault
      * @param isMandatory
      * @return
      */
    def getProtoType(
        attributeName: String,
        reference: Int,
        referenceValueId: Option[Int],
        maybeDefault: Option[String],
        isMandatory: Boolean = false
    ): String = {
      val typeBuffer = new StringBuilder
      if (reference == String)
        typeBuffer.append("String")
      if (reference == Integer)
        typeBuffer.append("Integer")
      if (reference == Amount)
        typeBuffer.append("Amount")
      if (reference == ID)
        typeBuffer.append("ID")
      if (reference == Text)
        typeBuffer.append("Text")
      if (reference == Date)
        typeBuffer.append("Date")
      if (reference == DateTime)
        typeBuffer.append("DateTime")
      if (reference == List)
        typeBuffer.append("List")
      if (reference == Table)
        typeBuffer.append("Table")
      if (reference == TableDir)
        typeBuffer.append("TableDir")
      if (reference == YesNo)
        typeBuffer.append("YesNo")
      if (reference == Location)
        typeBuffer.append("Location")
      if (reference == Number)
        typeBuffer.append("Number")
      if (reference == Binary)
        typeBuffer.append("Binary")
      if (reference == Time)
        typeBuffer.append("Time")
      if (reference == Account)
        typeBuffer.append("Account")
      if (reference == RowID)
        typeBuffer.append("RowID")
      if (reference == Color)
        typeBuffer.append("Color")
      if (reference == Button)
        typeBuffer.append("Button")
      if (reference == Quantity)
        typeBuffer.append("Quantity")
      if (reference == Search)
        typeBuffer.append("Search")
      if (reference == Locator)
        typeBuffer.append("Locator")
      if (reference == Image)
        typeBuffer.append("Image")
      if (reference == Assignment)
        typeBuffer.append("Assignment")
      if (reference == Memo)
        typeBuffer.append("Memo")
      if (reference == PAttribute)
        typeBuffer.append("ProductAttribute")
      if (reference == TextLong)
        typeBuffer.append("TextLong")
      if (reference == CostPrice)
        typeBuffer.append("CostPrice")
      if (reference == FilePath)
        typeBuffer.append("FilePath")
      if (reference == FileName)
        typeBuffer.append("FileName")
      if (reference == URL)
        typeBuffer.append("URL")
      if (reference == PrinterName)
        typeBuffer.append("PrinterName")
      if (reference == Chart)
        typeBuffer.append("Chart")
      typeBuffer.toString()
    }

    /**
      * Check if Is a Text
      *
      * @param reference Reference
      * @return
      */
    def isText(reference: Int): Boolean =
      reference match {
        case String      => true
        case Text        => true
        case TextLong    => true
        case Memo        => true
        case FilePath    => true
        case FileName    => true
        case URL         => true
        case PrinterName => true
        case _           => false
      }

    /**
      * Check if Is a Date
      *
      * @param reference Reference
      * @return
      */
    def isDate(reference: Int): Boolean =
      reference match {
        case Date     => true
        case DateTime => true
        case _        => false
      }

    /**
      * Check if Is Lookup
      *
      * @param reference Reference
      * @return
      */
    def isLookup(reference: Int): Boolean =
      reference match {
        case List     => true
        case Table    => true
        case TableDir => true
        case Search   => true
        case _        => false
      }

    /**
      * Check if is Lob
      *
      * @param reference Reference
      * @return
      */
    def isLob(reference: Int): Boolean =
      reference match {
        case Binary   => true
        case TextLong => true
        case _        => false
      }

    /**
      * Check if is Numeric
      *
      * @param reference Reference
      * @return
      */
    def isNumeric(reference: Int): Boolean =
      reference match {
        case Amount    => true
        case Number    => true
        case CostPrice => true
        case Integer   => true
        case Quantity  => true
        case _         => false
      }

    /**
      * Check if is an Id
      *
      * @param reference Reference
      * @return
      */
    def isId(reference: Int): Boolean =
      reference match {
        case ID         => true
        case Table      => true
        case TableDir   => true
        case Search     => true
        case Location   => true
        case Locator    => true
        case Account    => true
        case Assignment => true
        case PAttribute => true
        case Image      => true
        case Color      => true
        case _          => false
      }

  }

}
