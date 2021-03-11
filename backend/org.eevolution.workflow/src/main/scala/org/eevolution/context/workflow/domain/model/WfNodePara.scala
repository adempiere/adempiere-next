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
* WfNodePara Algebraic Data Types domain model
* Workflow Node Parameter
* Workflow Node Execution Parameter
* 
*/

case class WfNodePara(
				wfNodeId : TableDirect, //AD_WF_Node_ID - Node - Workflow Node (activity), step or process
				createdBy : Table, //CreatedBy - Created By - User who created this records
				created : DateTime, //Created - Created - Date this record was created
				description : Option[String], //Description - Description - Optional short description of the record
				attributeName : Option[String], //AttributeName - Attribute Name - Name of the Attribute
				wfNodeParaId : Id, //AD_WF_Node_Para_ID - Workflow Node Parameter - Workflow Node Execution Parameter
				orgId : TableDirect, //AD_Org_ID - Organization - Organizational entity within client
				processParaId : Option[TableDirect], //AD_Process_Para_ID - Process Parameter - 
				clientId : TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
				updatedBy : Table, //UpdatedBy - Updated By - User who updated this records
				isActive : YesNo = true, //IsActive - Active - The record is active in the system
				updated : DateTime, //Updated - Updated - Date this record was updated
				attributeValue : Option[String], //AttributeValue - Attribute Value - Value of the Attribute
				entityType : String, //EntityType - Entity Type - Dictionary Entity Type; Determines ownership and synchronization
				uuid : Option[String], //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
) extends DomainModel
with ActiveEnabled
with Identifiable
with Traceable {
	override type ActiveEnabled = this.type
	override type Traceable = this.type
	override type Identifiable = this.type
	override val entityName: String = "AD_WF_Node_Para"
	override val identifier: String = "AD_WF_Node_Para_ID"
	override val Id: Id = wfNodeParaId
}


object WfNodePara {
	def create(
			wfNodeId : TableDirect, //AD_WF_Node_ID - Node - Workflow Node (activity), step or process
			createdBy : Table, //CreatedBy - Created By - User who created this records
			created : DateTime, //Created - Created - Date this record was created
			description : Option[String], //Description - Description - Optional short description of the record
			attributeName : Option[String], //AttributeName - Attribute Name - Name of the Attribute
			wfNodeParaId : Id, //AD_WF_Node_Para_ID - Workflow Node Parameter - Workflow Node Execution Parameter
			orgId : TableDirect, //AD_Org_ID - Organization - Organizational entity within client
			processParaId : Option[TableDirect], //AD_Process_Para_ID - Process Parameter - 
			clientId : TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
			updatedBy : Table, //UpdatedBy - Updated By - User who updated this records
			isActive : YesNo = true, //IsActive - Active - The record is active in the system
			updated : DateTime, //Updated - Updated - Date this record was updated
			attributeValue : Option[String], //AttributeValue - Attribute Value - Value of the Attribute
			entityType : String, //EntityType - Entity Type - Dictionary Entity Type; Determines ownership and synchronization
			uuid : Option[String] //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
	) : WfNodePara = WfNodePara(
						wfNodeId, //AD_WF_Node_ID - Node - Workflow Node (activity), step or process
						createdBy, //CreatedBy - Created By - User who created this records
						created, //Created - Created - Date this record was created
						description, //Description - Description - Optional short description of the record
						attributeName, //AttributeName - Attribute Name - Name of the Attribute
						wfNodeParaId, //AD_WF_Node_Para_ID - Workflow Node Parameter - Workflow Node Execution Parameter
						orgId, //AD_Org_ID - Organization - Organizational entity within client
						processParaId, //AD_Process_Para_ID - Process Parameter - 
						clientId, //AD_Client_ID - Client - Client/Tenant for this installation.
						updatedBy, //UpdatedBy - Updated By - User who updated this records
						isActive, //IsActive - Active - The record is active in the system
						updated, //Updated - Updated - Date this record was updated
						attributeValue, //AttributeValue - Attribute Value - Value of the Attribute
						entityType, //EntityType - Entity Type - Dictionary Entity Type; Determines ownership and synchronization
						uuid)  //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
}
