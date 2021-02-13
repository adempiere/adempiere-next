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
  * Modified by emeris.hernandez@e-evolution.com , www.e-evolution.com on 19/10/17.
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
  * Entity for Entity Type
  * @param entityTypeId Entity Type ID
  * @param tenantId Tenant ID
  * @param organizationId Organization ID
  * @param entityType Entity Type
  * @param isActive Is Active
  * @param created Created
  * @param createdBy Created By
  * @param updated Updated
  * @param updatedBy Updated By
  * @param name Name
  * @param description Description
  * @param help Help
  * @param version Version
  * @param modelPackage Model Package
  * @param classPath Class Path
  * @param processing Processing
  * @param uuid UUID
  */
case class EntityType(
    entityTypeId: Int,
    tenantId: Int,
    organizationId: Int,
    entityType: String = EntityType.Dictionary,
    isActive: Boolean = true,
    created: DateTime = LocalDateTime.now,
    createdBy: Int,
    updated: DateTime = LocalDateTime.now,
    updatedBy: Int,
    name: String,
    description: Option[String],
    help: Option[String],
    version: Option[String],
    modelPackage: Option[String],
    classPath: Option[String],
    processing: Boolean = false,
    uuid: String
) extends DomainModel
    with ActiveEnabled
    with Identifiable
    with Traceable {
  override type ActiveEnabled = this.type
  override type Identifiable = this.type
  override type Traceable = this.type

  override def Id: Int = entityTypeId

  override val entityName: String = "AD_EntityType"
  override val identifier: String = "AD_EntityType_ID"
}

object EntityType {
  //implicit lazy val jsonFormat = Jsonx.formatCaseClass[EntityType]
  def Dictionary: String = "D"
  def create(
      entityTypeId: Int,
      tenantId: Int,
      organizationId: Int,
      entityType: String,
      isActive: Boolean,
      created: DateTime,
      createdBy: Int,
      updated: DateTime,
      updatedBy: Int,
      name: String,
      description: String,
      help: String,
      version: String,
      modelPackage: String,
      classPath: String,
      processing: Boolean,
      uuid: String
  ) =
    EntityType(
      entityTypeId,
      tenantId,
      organizationId,
      entityType,
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
      processing,
      uuid
    )
}
