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
 **/

package org.eevolution.context.kernel.domain.model

import java.time.LocalDateTime

import org.eevolution.context.kernel.domain.ubiquitouslanguage._

/**
  * Organization Entity
  *
  * @param organizationId        Organization ID
  * @param tenantId              Tenant ID
  * @param isActive              Is Active
  * @param created               Created
  * @param createdBy             Created By
  * @param updated               Updated
  * @param updatedBy             Updated By
  * @param value                 Value
  * @param name                  Name
  * @param description           Description
  * @param isSummary             Is Summary
  * @param replicationStrategyId Replication Strategy ID
  * @param parentOrganizationId  ParentOrganization ID
  * @param uuid                  UUID
  */
case class Organization(
    clientId: TableDirect, // AD_Client_ID
    organizationId: Id,
    tenantId: TableDirect,
    isActive: YesNo = true,
    created: DateTime = LocalDateTime.now,
    createdBy: Table,
    updated: DateTime = LocalDateTime.now,
    updatedBy: Table,
    value: String,
    name: String,
    description: String,
    isSummary: YesNo = true,
    replicationStrategyId: TableDirect,
    parentOrganizationId: Table,
    uuid: String
) extends DomainModel
    with ActiveEnabled
    with Identifiable
    with Traceable {
  override type ActiveEnabled = this.type
  override type Identifiable = this.type
  override type Traceable = this.type

  override def Id: Int = organizationId

  override val entityName: String = "AD_Org"
  override val identifier: String = "AD_Org_ID"
}

object Organization {
  //implicit lazy val jsonFormat = Jsonx.formatCaseClass[Organization]
  def create(
      clientId: TableDirect, // AD_Client_ID
      organizationId: Id,
      tenantId: TableDirect,
      isActive: YesNo,
      created: DateTime,
      createdBy: Table,
      updated: DateTime,
      updatedBy: Table,
      value: String,
      name: String,
      description: String,
      isSummary: YesNo,
      replicationStrategyId: TableDirect,
      parentOrganizationId: Table,
      uuid: String
  ) =
    Organization(
      clientId,
      organizationId,
      tenantId,
      isActive,
      created,
      createdBy,
      updated,
      updatedBy,
      value,
      name,
      description,
      isSummary,
      replicationStrategyId,
      parentOrganizationId,
      uuid
    )
}
