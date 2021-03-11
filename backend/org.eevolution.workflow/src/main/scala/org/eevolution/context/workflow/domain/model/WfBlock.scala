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
* WfBlock Algebraic Data Types domain model
* Workflow Block
* Workflow Transaction Execution Block
* 
*/

case class WfBlock(
				description : Option[String], //Description - Description - Optional short description of the record
				clientId : TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
				workflowId : TableDirect, //AD_Workflow_ID - Workflow - Workflow or combination of tasks
				updated : DateTime, //Updated - Updated - Date this record was updated
				createdBy : Table, //CreatedBy - Created By - User who created this records
				isActive : YesNo = true, //IsActive - Active - The record is active in the system
				name : String, //Name - Name - Alphanumeric identifier of the entity
				created : DateTime, //Created - Created - Date this record was created
				orgId : TableDirect, //AD_Org_ID - Organization - Organizational entity within client
				wfBlockId : Id, //AD_WF_Block_ID - Workflow Block - Workflow Transaction Execution Block
				updatedBy : Table, //UpdatedBy - Updated By - User who updated this records
				uuid : Option[String], //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
) extends DomainModel
with ActiveEnabled
with Identifiable
with Traceable {
	override type ActiveEnabled = this.type
	override type Traceable = this.type
	override type Identifiable = this.type
	override val entityName: String = "AD_WF_Block"
	override val identifier: String = "AD_WF_Block_ID"
	override val Id: Id = wfBlockId
}


object WfBlock {
	def create(
			description : Option[String], //Description - Description - Optional short description of the record
			clientId : TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
			workflowId : TableDirect, //AD_Workflow_ID - Workflow - Workflow or combination of tasks
			updated : DateTime, //Updated - Updated - Date this record was updated
			createdBy : Table, //CreatedBy - Created By - User who created this records
			isActive : YesNo = true, //IsActive - Active - The record is active in the system
			name : String, //Name - Name - Alphanumeric identifier of the entity
			created : DateTime, //Created - Created - Date this record was created
			orgId : TableDirect, //AD_Org_ID - Organization - Organizational entity within client
			wfBlockId : Id, //AD_WF_Block_ID - Workflow Block - Workflow Transaction Execution Block
			updatedBy : Table, //UpdatedBy - Updated By - User who updated this records
			uuid : Option[String] //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
	) : WfBlock = WfBlock(
						description, //Description - Description - Optional short description of the record
						clientId, //AD_Client_ID - Client - Client/Tenant for this installation.
						workflowId, //AD_Workflow_ID - Workflow - Workflow or combination of tasks
						updated, //Updated - Updated - Date this record was updated
						createdBy, //CreatedBy - Created By - User who created this records
						isActive, //IsActive - Active - The record is active in the system
						name, //Name - Name - Alphanumeric identifier of the entity
						created, //Created - Created - Date this record was created
						orgId, //AD_Org_ID - Organization - Organizational entity within client
						wfBlockId, //AD_WF_Block_ID - Workflow Block - Workflow Transaction Execution Block
						updatedBy, //UpdatedBy - Updated By - User who updated this records
						uuid)  //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
}
