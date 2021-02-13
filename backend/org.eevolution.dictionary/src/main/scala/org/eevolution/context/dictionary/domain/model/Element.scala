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
  * Created by victor.perez@e-evolution.com , www.e-evolution.com.
  * Modified by emeris.hernandez@e-evolution.com , www.e-evolution.com on 13/10/17.
  */
package org.eevolution.context.dictionary.domain.model

import org.eevolution.context.kernel.domain.model.{
  ActiveEnabled,
  DomainModel,
  Identifiable,
  Traceable
}
import org.eevolution.context.kernel.domain.ubiquitouslanguage._

import java.time.LocalDateTime

/**
  * Element Entity
  * @param elementId Element ID
  * @param tenantId Tenant ID
  * @param organizationId Organization ID
  * @param isActive is Active
  * @param created Created
  * @param createdBy Created By
  * @param updated Updated
  * @param updatedBy Updated By
  * @param columnName Column Name
  * @param entityType Entity Type
  * @param name Name
  * @param printName Print Name
  * @param description Description
  * @param help Help
  * @param namePO Name PO
  * @param printNamePO Print Name PO
  * @param descriptionPO Description PO
  * @param helpPO Help PO
  * @param referenceId Reference ID
  * @param fieldLength Field Length
  * @param referenceValueId Reference Value ID
  * @param uuid UUID
  */

case class Element(
    elementId: Id,
    tenantId: TableDirect,
    organizationId: TableDirect,
    isActive: YesNo = true,
    created: DateTime = LocalDateTime.now,
    createdBy: Table,
    updated: DateTime = LocalDateTime.now,
    updatedBy: Int,
    columnName: String,
    entityType: String,
    name: String,
    printName: Option[String],
    description: Option[String],
    help: Option[String],
    namePO: Option[String],
    printNamePO: Option[String],
    descriptionPO: Option[String],
    helpPO: Option[String],
    referenceId: Option[Int],
    fieldLength: Option[Int],
    referenceValueId: Option[Int],
    uuid: String
) extends DomainModel
    with ActiveEnabled
    with Identifiable
    with Traceable {
  override type DomainModel = this.type
  override type ActiveEnabled = this.type
  override type Identifiable = this.type
  override type Traceable = this.type
  override def Id: Int = elementId

  override val entityName: String = "AD_Element"
  override val identifier: String = "AD_Element_ID"
}

object Element {
  //implicit val format: Format[Element] = Json.format
  def create(
      elementId: Int,
      tenantId: Int,
      organizationId: Int,
      isActive: Boolean,
      created: DateTime,
      createdBy: Int,
      updated: DateTime,
      updatedBy: Int,
      columnName: String,
      entityType: String,
      name: String,
      printName: String,
      description: String,
      help: String,
      namePO: String,
      printNamePO: String,
      descriptionPO: String,
      helpPO: String,
      referenceId: Int,
      fieldLength: Int,
      referenceValueId: Int,
      uuid: String
  ) =
    Element(
      elementId,
      tenantId,
      organizationId,
      isActive,
      created,
      createdBy,
      updated,
      updatedBy,
      columnName,
      entityType,
      name,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      uuid
    )
}
