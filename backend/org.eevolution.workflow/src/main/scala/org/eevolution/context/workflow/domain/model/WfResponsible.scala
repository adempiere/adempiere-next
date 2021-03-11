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

package org.eevolution.context.workflow.domain.model

import org.eevolution.context.kernel.domain.model._
import org.eevolution.context.kernel.domain.ubiquitouslanguage._

/**
* WfResponsible Algebraic Data Types domain model
* Workflow Responsible
* Responsible for Workflow Execution
* 
*/

case class WfResponsible(
				isActive : YesNo = true, //IsActive - Active - The record is active in the system
				responsibleType : ListType, //ResponsibleType - Responsible Type - Type of the Responsibility for a workflow
				createdBy : Table, //CreatedBy - Created By - User who created this records
				name : String, //Name - Name - Alphanumeric identifier of the entity
				created : DateTime, //Created - Created - Date this record was created
				userId : Option[Search], //AD_User_ID - User/Contact - User within the system - Internal or Business Partner Contact
				roleId : TableDirect, //AD_Role_ID - Role - Responsibility Role
				description : Option[String], //Description - Description - Optional short description of the record
				clientId : TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
				updated : DateTime, //Updated - Updated - Date this record was updated
				orgId : TableDirect, //AD_Org_ID - Organization - Organizational entity within client
				wfResponsibleId : Id, //AD_WF_Responsible_ID - Workflow Responsible - Responsible for Workflow Execution
				updatedBy : Table, //UpdatedBy - Updated By - User who updated this records
				entityType : String, //EntityType - Entity Type - Dictionary Entity Type; Determines ownership and synchronization
				uuid : Option[String], //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
) extends DomainModel
with ActiveEnabled
with Identifiable
with Traceable {
	override type ActiveEnabled = this.type
	override type Traceable = this.type
	override type Identifiable = this.type
	override val entityName: String = "AD_WF_Responsible"
	override val identifier: String = "AD_WF_Responsible_ID"
	override val Id: Id = wfResponsibleId
}


object WfResponsible {
	def create(
			isActive : YesNo = true, //IsActive - Active - The record is active in the system
			responsibleType : ListType, //ResponsibleType - Responsible Type - Type of the Responsibility for a workflow
			createdBy : Table, //CreatedBy - Created By - User who created this records
			name : String, //Name - Name - Alphanumeric identifier of the entity
			created : DateTime, //Created - Created - Date this record was created
			userId : Option[Search], //AD_User_ID - User/Contact - User within the system - Internal or Business Partner Contact
			roleId : TableDirect, //AD_Role_ID - Role - Responsibility Role
			description : Option[String], //Description - Description - Optional short description of the record
			clientId : TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
			updated : DateTime, //Updated - Updated - Date this record was updated
			orgId : TableDirect, //AD_Org_ID - Organization - Organizational entity within client
			wfResponsibleId : Id, //AD_WF_Responsible_ID - Workflow Responsible - Responsible for Workflow Execution
			updatedBy : Table, //UpdatedBy - Updated By - User who updated this records
			entityType : String, //EntityType - Entity Type - Dictionary Entity Type; Determines ownership and synchronization
			uuid : Option[String] //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
	) : WfResponsible = WfResponsible(
						isActive, //IsActive - Active - The record is active in the system
						responsibleType, //ResponsibleType - Responsible Type - Type of the Responsibility for a workflow
						createdBy, //CreatedBy - Created By - User who created this records
						name, //Name - Name - Alphanumeric identifier of the entity
						created, //Created - Created - Date this record was created
						userId, //AD_User_ID - User/Contact - User within the system - Internal or Business Partner Contact
						roleId, //AD_Role_ID - Role - Responsibility Role
						description, //Description - Description - Optional short description of the record
						clientId, //AD_Client_ID - Client - Client/Tenant for this installation.
						updated, //Updated - Updated - Date this record was updated
						orgId, //AD_Org_ID - Organization - Organizational entity within client
						wfResponsibleId, //AD_WF_Responsible_ID - Workflow Responsible - Responsible for Workflow Execution
						updatedBy, //UpdatedBy - Updated By - User who updated this records
						entityType, //EntityType - Entity Type - Dictionary Entity Type; Determines ownership and synchronization
						uuid)  //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
}
