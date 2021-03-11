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
* WfActivity Algebraic Data Types domain model
* Workflow Activity
* Workflow Activity
* 
*/

case class WfActivity(
				wfNodeId : TableDirect, //AD_WF_Node_ID - Node - Workflow Node (activity), step or process
				wfResponsibleId : Option[TableDirect], //AD_WF_Responsible_ID - Workflow Responsible - Responsible for Workflow Execution
				orgId : TableDirect, //AD_Org_ID - Organization - Organizational entity within client
				createdBy : Table, //CreatedBy - Created By - User who created this records
				userId : Option[Search], //AD_User_ID - User/Contact - User within the system - Internal or Business Partner Contact
				wfActivityId : Id, //AD_WF_Activity_ID - Workflow Activity - Workflow Activity
				wfProcessId : TableDirect, //AD_WF_Process_ID - Workflow Process - Actual Workflow Process Instance
				isActive : YesNo = true, //IsActive - Active - The record is active in the system
				processed : YesNo, //Processed - Processed - The document has been processed
				processing : Option[String], //Processing - Process Now - 
				wfState : ListType, //WFState - Workflow State - State of the execution of the workflow
				created : DateTime, //Created - Created - Date this record was created
				updatedBy : Table, //UpdatedBy - Updated By - User who updated this records
				clientId : TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
				messageId : Option[TableDirect], //AD_Message_ID - Message - System Message
				updated : DateTime, //Updated - Updated - Date this record was updated
				textMsg : Option[String], //TextMsg - Text Message - Text Message
				workflowId : TableDirect, //AD_Workflow_ID - Workflow - Workflow or combination of tasks
				priority : Option[Int], //Priority - Priority - Indicates if this request is of a high, medium or low priority.
				recordId : String, //Record_ID - Record ID - Direct internal record ID
				tableId : Search, //AD_Table_ID - Table - Database Table information
				endWaitTime : Option[DateTime], //EndWaitTime - End Wait - End of sleep time
				dateLastAlert : Option[DateTime], //DateLastAlert - Last Alert - Date when last alert were sent
				dynPriorityStart : Option[Int], //DynPriorityStart - Dyn Priority Start - Starting priority before changed dynamically
				uuid : Option[String], //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
) extends DomainModel
with ActiveEnabled
with Identifiable
with Traceable {
	override type ActiveEnabled = this.type
	override type Traceable = this.type
	override type Identifiable = this.type
	override val entityName: String = "AD_WF_Activity"
	override val identifier: String = "AD_WF_Activity_ID"
	override val Id: Id = wfActivityId
}


object WfActivity {
	def create(
			wfNodeId : TableDirect, //AD_WF_Node_ID - Node - Workflow Node (activity), step or process
			wfResponsibleId : Option[TableDirect], //AD_WF_Responsible_ID - Workflow Responsible - Responsible for Workflow Execution
			orgId : TableDirect, //AD_Org_ID - Organization - Organizational entity within client
			createdBy : Table, //CreatedBy - Created By - User who created this records
			userId : Option[Search], //AD_User_ID - User/Contact - User within the system - Internal or Business Partner Contact
			wfActivityId : Id, //AD_WF_Activity_ID - Workflow Activity - Workflow Activity
			wfProcessId : TableDirect, //AD_WF_Process_ID - Workflow Process - Actual Workflow Process Instance
			isActive : YesNo = true, //IsActive - Active - The record is active in the system
			processed : YesNo, //Processed - Processed - The document has been processed
			processing : Option[String], //Processing - Process Now - 
			wfState : ListType, //WFState - Workflow State - State of the execution of the workflow
			created : DateTime, //Created - Created - Date this record was created
			updatedBy : Table, //UpdatedBy - Updated By - User who updated this records
			clientId : TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
			messageId : Option[TableDirect], //AD_Message_ID - Message - System Message
			updated : DateTime, //Updated - Updated - Date this record was updated
			textMsg : Option[String], //TextMsg - Text Message - Text Message
			workflowId : TableDirect, //AD_Workflow_ID - Workflow - Workflow or combination of tasks
			priority : Option[Int], //Priority - Priority - Indicates if this request is of a high, medium or low priority.
			recordId : String, //Record_ID - Record ID - Direct internal record ID
			tableId : Search, //AD_Table_ID - Table - Database Table information
			endWaitTime : Option[DateTime], //EndWaitTime - End Wait - End of sleep time
			dateLastAlert : Option[DateTime], //DateLastAlert - Last Alert - Date when last alert were sent
			dynPriorityStart : Option[Int], //DynPriorityStart - Dyn Priority Start - Starting priority before changed dynamically
			uuid : Option[String] //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
	) : WfActivity = WfActivity(
						wfNodeId, //AD_WF_Node_ID - Node - Workflow Node (activity), step or process
						wfResponsibleId, //AD_WF_Responsible_ID - Workflow Responsible - Responsible for Workflow Execution
						orgId, //AD_Org_ID - Organization - Organizational entity within client
						createdBy, //CreatedBy - Created By - User who created this records
						userId, //AD_User_ID - User/Contact - User within the system - Internal or Business Partner Contact
						wfActivityId, //AD_WF_Activity_ID - Workflow Activity - Workflow Activity
						wfProcessId, //AD_WF_Process_ID - Workflow Process - Actual Workflow Process Instance
						isActive, //IsActive - Active - The record is active in the system
						processed, //Processed - Processed - The document has been processed
						processing, //Processing - Process Now - 
						wfState, //WFState - Workflow State - State of the execution of the workflow
						created, //Created - Created - Date this record was created
						updatedBy, //UpdatedBy - Updated By - User who updated this records
						clientId, //AD_Client_ID - Client - Client/Tenant for this installation.
						messageId, //AD_Message_ID - Message - System Message
						updated, //Updated - Updated - Date this record was updated
						textMsg, //TextMsg - Text Message - Text Message
						workflowId, //AD_Workflow_ID - Workflow - Workflow or combination of tasks
						priority, //Priority - Priority - Indicates if this request is of a high, medium or low priority.
						recordId, //Record_ID - Record ID - Direct internal record ID
						tableId, //AD_Table_ID - Table - Database Table information
						endWaitTime, //EndWaitTime - End Wait - End of sleep time
						dateLastAlert, //DateLastAlert - Last Alert - Date when last alert were sent
						dynPriorityStart, //DynPriorityStart - Dyn Priority Start - Starting priority before changed dynamically
						uuid)  //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
}
