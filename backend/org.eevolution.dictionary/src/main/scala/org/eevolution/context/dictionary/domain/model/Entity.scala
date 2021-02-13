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
  * Entity for Application Dictionary
  * @param entityId Entity ID
  * @param tenantId Tenant ID
  * @param organizationId Organization ID
  * @param isActive Is Active
  * @param created Created
  * @param createdBy Created By
  * @param updated Updated
  * @param updatedBy Updated By
  * @param name Name
  * @param description Description
  * @param help Help
  * @param tableName Table Name
  * @param isView Is View
  * @param accessLevel Access Level
  * @param entityType Entity Type
  * @param windowId Window ID
  * @param windowPOId Window PO ID
  * @param valueRuleId Value Rule ID
  * @param loadSeq Load Seq
  * @param isSecurityEnabled Is Security Enabled
  * @param isDeleteable Is Deleteable
  * @param isHighVolume Is High Volume
  * @param isImportTable Is Import Table
  * @param isChangeLog Is Change Log
  * @param replicationType Replication Type
  * @param copyColumnsFromTable Copy Columns From Table
  * @param isCentrallyMaintained Is Centrally Maintained
  * @param acTriggerLength AC Trigger Length
  * @param isDocument Is Document
  * @param isIgnoreMigration Is Ignore Migration
  * @param uuid UUID
  */

case class Entity(
    entityId: Int,
    tenantId: Int,
    organizationId: Int,
    isActive: Boolean = true,
    created: DateTime = LocalDateTime.now,
    createdBy: Int,
    updated: DateTime = LocalDateTime.now,
    updatedBy: Int,
    name: String,
    description: Option[String],
    help: Option[String],
    tableName: String,
    isView: Boolean = false,
    accessLevel: String,
    entityType: String,
    windowId: Option[Int],
    windowPOId: Option[Int],
    valueRuleId: Option[Int],
    loadSeq: Option[Int],
    isSecurityEnabled: Boolean = false,
    isDeleteable: Boolean = false,
    isHighVolume: Boolean = false,
    isImportTable: Boolean = false,
    isChangeLog: Boolean = false,
    replicationType: String = "L",
    copyColumnsFromTable: Option[String],
    isCentrallyMaintained: Boolean = true,
    acTriggerLength: Int,
    isDocument: Boolean = false,
    isIgnoreMigration: Boolean = false,
    uuid: String
) extends DomainModel
    with ActiveEnabled
    with Identifiable
    with Traceable {
  override type ActiveEnabled = this.type
  override type Identifiable = this.type
  override type Traceable = this.type
  override def Id: Int = entityId

  override val entityName: String = "AD_Table"
  override val identifier: String = "AD_Table_ID"
}

/**
  * Entity Singleton
  */
object Entity {
  //implicit lazy val jsonFormat = Jsonx.formatCaseClass[Entity]
  def create(
      entityId: Int,
      tenantId: Int,
      organizationId: Int,
      isActive: Boolean,
      created: DateTime,
      createdBy: Int,
      updated: DateTime,
      updatedBy: Int,
      name: String,
      description: String,
      help: String,
      tableName: String,
      isView: Boolean,
      accessLevel: String,
      entityType: String,
      windowId: Int,
      windowPOId: Int,
      valueRuleId: Int,
      loadSeq: Int,
      isSecurityEnabled: Boolean,
      isDeleteable: Boolean,
      isHighVolume: Boolean,
      isImportTable: Boolean,
      isChangeLog: Boolean,
      replicationType: String,
      copyColumnsFromTable: String,
      isCentrallyMaintained: Boolean,
      acTriggerLength: Int,
      isDocument: Boolean,
      isIgnoreMigration: Boolean,
      uuid: String
  ) =
    Entity(
      entityId,
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
      tableName,
      isView,
      accessLevel,
      entityType,
      None,
      None,
      None,
      None,
      isSecurityEnabled,
      isDeleteable,
      isHighVolume,
      isImportTable,
      isChangeLog,
      replicationType,
      None,
      isCentrallyMaintained,
      acTriggerLength,
      isDocument,
      isIgnoreMigration,
      uuid
    )

}
