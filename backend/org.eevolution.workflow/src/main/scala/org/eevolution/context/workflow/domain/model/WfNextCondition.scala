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
* WfNextCondition Algebraic Data Types domain model
* Transition Condition
* Workflow Node Transition Condition
* 
*/

case class WfNextCondition(
				createdBy : Table, //CreatedBy - Created By - User who created this records
				wfNextConditionId : Id, //AD_WF_NextCondition_ID - Transition Condition - Workflow Node Transition Condition
				andOr : ListType = "O", //AndOr - And/Or - Logical operation: AND or OR
				created : DateTime, //Created - Created - Date this record was created
				operation : ListType, //Operation - Operation - Compare Operation
				orgId : TableDirect, //AD_Org_ID - Organization - Organizational entity within client
				clientId : TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
				wfNodeNextId : TableDirect, //AD_WF_NodeNext_ID - Node Transition - Workflow Node Transition
				seqNo : Int, //SeqNo - Sequence - Method of ordering records; lowest number comes first
				value : String, //Value - Search Key - Search key for the record in the format required - must be unique
				updatedBy : Table, //UpdatedBy - Updated By - User who updated this records
				isActive : YesNo = true, //IsActive - Active - The record is active in the system
				value2 : Option[String], //Value2 - Value To - Value To
				entityType : String, //EntityType - Entity Type - Dictionary Entity Type; Determines ownership and synchronization
				updated : DateTime, //Updated - Updated - Date this record was updated
				columnId : TableDirect, //AD_Column_ID - Column - Column in the table
				uuid : Option[String], //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
) extends DomainModel
with ActiveEnabled
with Identifiable
with Traceable {
	override type ActiveEnabled = this.type
	override type Traceable = this.type
	override type Identifiable = this.type
	override val entityName: String = "AD_WF_NextCondition"
	override val identifier: String = "AD_WF_NextCondition_ID"
	override val Id: Id = wfNextConditionId
}


object WfNextCondition {
	def create(
			createdBy : Table, //CreatedBy - Created By - User who created this records
			wfNextConditionId : Id, //AD_WF_NextCondition_ID - Transition Condition - Workflow Node Transition Condition
			andOr : ListType = "O", //AndOr - And/Or - Logical operation: AND or OR
			created : DateTime, //Created - Created - Date this record was created
			operation : ListType, //Operation - Operation - Compare Operation
			orgId : TableDirect, //AD_Org_ID - Organization - Organizational entity within client
			clientId : TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
			wfNodeNextId : TableDirect, //AD_WF_NodeNext_ID - Node Transition - Workflow Node Transition
			seqNo : Int, //SeqNo - Sequence - Method of ordering records; lowest number comes first
			value : String, //Value - Search Key - Search key for the record in the format required - must be unique
			updatedBy : Table, //UpdatedBy - Updated By - User who updated this records
			isActive : YesNo = true, //IsActive - Active - The record is active in the system
			value2 : Option[String], //Value2 - Value To - Value To
			entityType : String, //EntityType - Entity Type - Dictionary Entity Type; Determines ownership and synchronization
			updated : DateTime, //Updated - Updated - Date this record was updated
			columnId : TableDirect, //AD_Column_ID - Column - Column in the table
			uuid : Option[String] //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
	) : WfNextCondition = WfNextCondition(
						createdBy, //CreatedBy - Created By - User who created this records
						wfNextConditionId, //AD_WF_NextCondition_ID - Transition Condition - Workflow Node Transition Condition
						andOr, //AndOr - And/Or - Logical operation: AND or OR
						created, //Created - Created - Date this record was created
						operation, //Operation - Operation - Compare Operation
						orgId, //AD_Org_ID - Organization - Organizational entity within client
						clientId, //AD_Client_ID - Client - Client/Tenant for this installation.
						wfNodeNextId, //AD_WF_NodeNext_ID - Node Transition - Workflow Node Transition
						seqNo, //SeqNo - Sequence - Method of ordering records; lowest number comes first
						value, //Value - Search Key - Search key for the record in the format required - must be unique
						updatedBy, //UpdatedBy - Updated By - User who updated this records
						isActive, //IsActive - Active - The record is active in the system
						value2, //Value2 - Value To - Value To
						entityType, //EntityType - Entity Type - Dictionary Entity Type; Determines ownership and synchronization
						updated, //Updated - Updated - Date this record was updated
						columnId, //AD_Column_ID - Column - Column in the table
						uuid)  //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
}
