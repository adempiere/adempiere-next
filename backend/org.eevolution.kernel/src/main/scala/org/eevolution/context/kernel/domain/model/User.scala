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
  * User Entity
  *
  * @param userId      Currency ID
  * @param tenantId    Tenant ID
  * @param isActive    Is Active
  * @param created     Created
  * @param createdBy   Created By
  * @param updated     Updated
  * @param updatedBy   Updated By
  * @param name        Name
  * @param description Description
  * @param uuid        UUID
  */
case class User(
    userId: Id,
    partnerId: Id,
    tenantId: TableDirect,
    organizationId: TableDirect,
    isActive: YesNo = true,
    created: DateTime = LocalDateTime.now,
    createdBy: Table,
    updated: DateTime = LocalDateTime.now,
    updatedBy: Table,
    name: String,
    description: String,
    email: String,
    password: String,
    salt: String,
    uuid: String
) extends DomainModel
    with ActiveEnabled
    with Identifiable
    with Traceable {
  override type ActiveEnabled = this.type
  override type Identifiable = this.type
  override type Traceable = this.type

  override def Id: Int = userId

  override val entityName: String = "AD_User"
  override val identifier: String = "AD_User_ID"
}

object User {
  //implicit lazy val jsonFormat = Jsonx.formatCaseClass[Currency]
  def create(
      userId: Id,
      partnerId: TableDirect,
      tenantId: TableDirect,
      organizationId: TableDirect,
      isActive: YesNo,
      created: DateTime,
      createdBy: Table,
      updated: DateTime,
      updatedBy: Id,
      name: String,
      description: String,
      email: String,
      password: String,
      salt: String,
      uuid: String
  ) =
    User(
      userId,
      partnerId,
      tenantId,
      organizationId,
      isActive,
      created,
      createdBy,
      updated,
      updatedBy,
      name,
      description,
      email,
      password,
      salt,
      uuid
    )
}
