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
  * Email: emeris.hernandez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
  * Created by emeris.hernandez@e-evolution.com , www.e-evolution.com
  */

package org.eevolution.context.dictionary.domain.model

import java.time.LocalDateTime

import org.eevolution.context.kernel.domain.model.{
  ActiveEnabled,
  DomainModel,
  Identifiable,
  Traceable
}
import org.eevolution.context.kernel.domain.ubiquitouslanguage.DateTime

/**
  * Element TRL Entity
  * @param elementId Element ID
  * @param language Language
  * @param tenantId Tenant ID
  * @param organizationId Organization ID
  * @param isActive Is Active
  * @param created Created
  * @param createdBy Created By
  * @param updated Updated
  * @param updatedBy Updated By
  * @param name Name
  * @param printName Print Name
  * @param description Description
  * @param help Help
  * @param namePO Name PO
  * @param printNamePO Print Name PO
  * @param descriptionPO Description PO
  * @param helpPO Help PO
  * @param isTranslated Is Translated
  * @param uuid UUID
  */

case class ElementTrl(
    elementId: Int,
    language: String,
    tenantId: Int,
    organizationId: Int,
    isActive: Boolean = true,
    created: DateTime = LocalDateTime.now,
    createdBy: Int,
    updated: DateTime = LocalDateTime.now,
    updatedBy: Int,
    name: String,
    printName: Option[String],
    description: Option[String],
    help: Option[String],
    namePO: Option[String],
    printNamePO: Option[String],
    descriptionPO: Option[String],
    helpPO: Option[String],
    isTranslated: Boolean = false,
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

  override val entityName: String = "AD_Element_Trl"
  override val identifier: String = null
}

object ElementTrl {
  //implicit lazy val jsonFormat = Jsonx.formatCaseClass[ElementTrl]
  def create(
      elementId: Int,
      language: String,
      tenantId: Int,
      organizationId: Int,
      isActive: Boolean,
      created: DateTime,
      createdBy: Int,
      updated: DateTime,
      updatedBy: Int,
      name: String,
      printName: String,
      description: String,
      help: String,
      namePO: String,
      printNamePO: String,
      descriptionPO: String,
      helpPO: String,
      isTranslated: Boolean,
      uuid: String
  ) =
    ElementTrl(
      elementId,
      language,
      tenantId,
      organizationId,
      isActive,
      created,
      createdBy,
      updated,
      updatedBy,
      name,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      isTranslated,
      uuid
    )
}
