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
* WfNodeNext Algebraic Data Types domain model
* Node Transition
* Workflow Node Transition
* 
*/

case class WfNodeNext(
				wfNodeId : TableDirect, //AD_WF_Node_ID - Node - Workflow Node (activity), step or process
				isActive : YesNo = true, //IsActive - Active - The record is active in the system
				created : DateTime, //Created - Created - Date this record was created
				createdBy : Table, //CreatedBy - Created By - User who created this records
				updated : DateTime, //Updated - Updated - Date this record was updated
				updatedBy : Table, //UpdatedBy - Updated By - User who updated this records
				clientId : TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
				orgId : TableDirect, //AD_Org_ID - Organization - Organizational entity within client
				wfNextId : Table, //AD_WF_Next_ID - Next Node - Next Node in workflow
				transitionCode : Option[String], //TransitionCode - Transition Code - Code resulting in TRUE of FALSE
				entityType : String, //EntityType - Entity Type - Dictionary Entity Type; Determines ownership and synchronization
				seqNo : Int, //SeqNo - Sequence - Method of ordering records; lowest number comes first
				description : Option[String], //Description - Description - Optional short description of the record
				wfNodeNextId : Id, //AD_WF_NodeNext_ID - Node Transition - Workflow Node Transition
				isStdUserWorkflow : YesNo, //IsStdUserWorkflow - Std User Workflow - Standard Manual User Approval Workflow
				uuid : Option[String], //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
) extends DomainModel
with ActiveEnabled
with Identifiable
with Traceable {
	override type ActiveEnabled = this.type
	override type Traceable = this.type
	override type Identifiable = this.type
	override val entityName: String = "AD_WF_NodeNext"
	override val identifier: String = "AD_WF_NodeNext_ID"
	override val Id: Id = wfNodeNextId
}


object WfNodeNext {
	def create(
			wfNodeId : TableDirect, //AD_WF_Node_ID - Node - Workflow Node (activity), step or process
			isActive : YesNo = true, //IsActive - Active - The record is active in the system
			created : DateTime, //Created - Created - Date this record was created
			createdBy : Table, //CreatedBy - Created By - User who created this records
			updated : DateTime, //Updated - Updated - Date this record was updated
			updatedBy : Table, //UpdatedBy - Updated By - User who updated this records
			clientId : TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
			orgId : TableDirect, //AD_Org_ID - Organization - Organizational entity within client
			wfNextId : Table, //AD_WF_Next_ID - Next Node - Next Node in workflow
			transitionCode : Option[String], //TransitionCode - Transition Code - Code resulting in TRUE of FALSE
			entityType : String, //EntityType - Entity Type - Dictionary Entity Type; Determines ownership and synchronization
			seqNo : Int, //SeqNo - Sequence - Method of ordering records; lowest number comes first
			description : Option[String], //Description - Description - Optional short description of the record
			wfNodeNextId : Id, //AD_WF_NodeNext_ID - Node Transition - Workflow Node Transition
			isStdUserWorkflow : YesNo, //IsStdUserWorkflow - Std User Workflow - Standard Manual User Approval Workflow
			uuid : Option[String] //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
	) : WfNodeNext = WfNodeNext(
						wfNodeId, //AD_WF_Node_ID - Node - Workflow Node (activity), step or process
						isActive, //IsActive - Active - The record is active in the system
						created, //Created - Created - Date this record was created
						createdBy, //CreatedBy - Created By - User who created this records
						updated, //Updated - Updated - Date this record was updated
						updatedBy, //UpdatedBy - Updated By - User who updated this records
						clientId, //AD_Client_ID - Client - Client/Tenant for this installation.
						orgId, //AD_Org_ID - Organization - Organizational entity within client
						wfNextId, //AD_WF_Next_ID - Next Node - Next Node in workflow
						transitionCode, //TransitionCode - Transition Code - Code resulting in TRUE of FALSE
						entityType, //EntityType - Entity Type - Dictionary Entity Type; Determines ownership and synchronization
						seqNo, //SeqNo - Sequence - Method of ordering records; lowest number comes first
						description, //Description - Description - Optional short description of the record
						wfNodeNextId, //AD_WF_NodeNext_ID - Node Transition - Workflow Node Transition
						isStdUserWorkflow, //IsStdUserWorkflow - Std User Workflow - Standard Manual User Approval Workflow
						uuid)  //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
}
