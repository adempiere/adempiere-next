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
import org.eevolution.context.kernel.domain.ubiquitouslanguage.{DateTime, Id}

/**
  * Attribute Entity
  * @param attributeId Attribute ID
  * @param tenantId Tenant ID
  * @param organizationId Organization ID
  * @param isActive Is Active
  * @param created Created
  * @param updated Updated
  * @param createdBy Created By
  * @param updatedBy Updated By
  * @param name Name
  * @param description Description
  * @param help Help
  * @param version Version
  * @param entityType Entity Type
  * @param attributeName Attribute Name
  * @param entityId Table ID
  * @param referenceId Reference ID
  * @param referenceValueId Reference Value ID
  * @param valRuleId Val Rule ID
  * @param fieldLength Field Length
  * @param defaultValue Default Value
  * @param isKey Is Key
  * @param isParent Is Parent
  * @param isMandatory Is Mandatory
  * @param isUpdateable Is Update Able
  * @param readOnlyLogic Read Only Logic
  * @param isIdentifier Is Identifier
  * @param seqNo Seq No
  * @param isTranslated Is Translated
  * @param isEncrypted Is Encrypted
  * @param callout Call Out
  * @param vFormat V Format
  * @param valueMin Value Min
  * @param valueMax Value Max
  * @param isSelectionAttribute is Selection Attribute
  * @param elementId Element ID
  * @param processId Process ID
  * @param isSyncDatabase Is Sync Database
  * @param isAlwaysUpdateable Is Always Update Able
  * @param attributeSql Attribute SQL
  * @param mandatoryLogic Mandatory Logic
  * @param infoFactoryClass Info Factory Class
  * @param isAutoComplete Is Auto Complete
  * @param formatPattern For Hat Pattern
  * @param chartId Chart ID
  * @param isRange Is Range
  * @param isAllowCopy Is Allow Copy
  * @param uuid UUID
  */

case class Attribute(
    attributeId: Int,
    tenantId: Int,
    organizationId: Int,
    isActive: Boolean = true,
    created: DateTime = LocalDateTime.now,
    updated: DateTime = LocalDateTime.now,
    createdBy: Int,
    updatedBy: Int,
    name: String,
    description: Option[String],
    help: Option[String],
    version: Int,
    entityType: String = "D",
    attributeName: String,
    entityId: Int,
    referenceId: Int,
    referenceValueId: Option[Int],
    valRuleId: Option[Int],
    fieldLength: Option[Int],
    defaultValue: Option[String],
    isKey: Boolean = false,
    isParent: Boolean = false,
    isMandatory: Boolean = false,
    isUpdateable: Boolean = true,
    readOnlyLogic: Option[String],
    isIdentifier: Boolean = false,
    seqNo: Option[Int],
    isTranslated: Boolean = false,
    isEncrypted: Boolean = false,
    callout: Option[String],
    vFormat: Option[String],
    valueMin: Option[String],
    valueMax: Option[String],
    isSelectionAttribute: Boolean = false,
    elementId: Option[Int],
    processId: Option[Int],
    isSyncDatabase: Boolean = false,
    isAlwaysUpdateable: Boolean = false,
    attributeSql: Option[String],
    mandatoryLogic: Option[String],
    infoFactoryClass: Option[String],
    isAutoComplete: Boolean = false,
    isAllowLogging: Boolean = true,
    formatPattern: Option[String],
    chartId: Option[Int],
    isRange: Boolean = false,
    isAllowCopy: Boolean = true,
    uuid: String
) extends DomainModel
    with ActiveEnabled
    with Identifiable
    with Traceable {
  override type ActiveEnabled = this.type
  override type Identifiable = this.type
  override type Traceable = this.type

  override def Id: Id = attributeId

  override val entityName: String = "AD_Column"
  override val identifier: String = "AD_Column_ID"
}

object Attribute {
  //implicit lazy val jsonFormat = Jsonx.formatCaseClass[Attribute]
  def create(
      attributeId: Int,
      tenantId: Int,
      organizationId: Int,
      isActive: Boolean,
      created: DateTime,
      updated: DateTime,
      createdBy: Int,
      updatedBy: Int,
      name: String,
      description: String,
      help: String,
      version: Int,
      entityType: String,
      attributeName: String,
      entityId: Int,
      referenceId: Int,
      referenceValueId: Int,
      valRuleId: Int,
      fieldLength: Int,
      defaultValue: String,
      isKey: Boolean,
      isParent: Boolean,
      isMandatory: Boolean,
      isUpdateable: Boolean,
      readOnlyLogic: String,
      isIdentifier: Boolean,
      seqNo: Int,
      isTranslated: Boolean,
      isEncrypted: Boolean,
      callout: String,
      vFormat: String,
      valueMin: String,
      valueMax: String,
      isSelectionAttribute: Boolean,
      elementId: Int,
      processId: Int,
      isSyncDatabase: Boolean,
      isAlwaysUpdateable: Boolean,
      attributeSql: String,
      mandatoryLogic: String,
      infoFactoryClass: String,
      isAutoComplete: Boolean,
      isAllowLogging: Boolean = true,
      formatPattern: String,
      chartId: Int,
      isRange: Boolean,
      isAllowCopy: Boolean,
      uuid: String
  ) =
    Attribute(
      attributeId,
      tenantId,
      organizationId,
      isActive,
      created,
      updated,
      createdBy,
      updatedBy,
      name,
      None,
      None,
      version,
      entityType,
      attributeName,
      entityId,
      referenceId,
      None,
      None,
      None,
      None,
      isKey,
      isParent,
      isMandatory,
      isUpdateable,
      None,
      isIdentifier,
      None,
      isTranslated,
      isEncrypted,
      None,
      None,
      None,
      None,
      isSelectionAttribute,
      None,
      None,
      isSyncDatabase,
      isAlwaysUpdateable,
      None,
      None,
      None,
      isAutoComplete,
      isAllowLogging,
      None,
      None,
      isRange,
      isAllowCopy,
      uuid
    )
}
