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
* WfProcess Algebraic Data Types domain model
* Workflow Process
* Actual Workflow Process Instance
* 
*/

case class WfProcess(
				created : DateTime, //Created - Created - Date this record was created
				processing : Option[String], //Processing - Process Now - 
				updated : DateTime, //Updated - Updated - Date this record was updated
				processed : YesNo, //Processed - Processed - The document has been processed
				isActive : YesNo = true, //IsActive - Active - The record is active in the system
				workflowId : TableDirect, //AD_Workflow_ID - Workflow - Workflow or combination of tasks
				clientId : TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
				createdBy : Table, //CreatedBy - Created By - User who created this records
				orgId : TableDirect, //AD_Org_ID - Organization - Organizational entity within client
				wfState : ListType, //WFState - Workflow State - State of the execution of the workflow
				userId : Option[Search], //AD_User_ID - User/Contact - User within the system - Internal or Business Partner Contact
				updatedBy : Table, //UpdatedBy - Updated By - User who updated this records
				wfResponsibleId : TableDirect, //AD_WF_Responsible_ID - Workflow Responsible - Responsible for Workflow Execution
				wfProcessId : Id, //AD_WF_Process_ID - Workflow Process - Actual Workflow Process Instance
				messageId : Option[TableDirect], //AD_Message_ID - Message - System Message
				textMsg : Option[String], //TextMsg - Text Message - Text Message
				tableId : Search, //AD_Table_ID - Table - Database Table information
				priority : Option[Int], //Priority - Priority - Indicates if this request is of a high, medium or low priority.
				recordId : String, //Record_ID - Record ID - Direct internal record ID
				uuid : Option[String], //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
) extends DomainModel
with ActiveEnabled
with Identifiable
with Traceable {
	override type ActiveEnabled = this.type
	override type Traceable = this.type
	override type Identifiable = this.type
	override val entityName: String = "AD_WF_Process"
	override val identifier: String = "AD_WF_Process_ID"
	override val Id: Id = wfProcessId
}


object WfProcess {
	def create(
			created : DateTime, //Created - Created - Date this record was created
			processing : Option[String], //Processing - Process Now - 
			updated : DateTime, //Updated - Updated - Date this record was updated
			processed : YesNo, //Processed - Processed - The document has been processed
			isActive : YesNo = true, //IsActive - Active - The record is active in the system
			workflowId : TableDirect, //AD_Workflow_ID - Workflow - Workflow or combination of tasks
			clientId : TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
			createdBy : Table, //CreatedBy - Created By - User who created this records
			orgId : TableDirect, //AD_Org_ID - Organization - Organizational entity within client
			wfState : ListType, //WFState - Workflow State - State of the execution of the workflow
			userId : Option[Search], //AD_User_ID - User/Contact - User within the system - Internal or Business Partner Contact
			updatedBy : Table, //UpdatedBy - Updated By - User who updated this records
			wfResponsibleId : TableDirect, //AD_WF_Responsible_ID - Workflow Responsible - Responsible for Workflow Execution
			wfProcessId : Id, //AD_WF_Process_ID - Workflow Process - Actual Workflow Process Instance
			messageId : Option[TableDirect], //AD_Message_ID - Message - System Message
			textMsg : Option[String], //TextMsg - Text Message - Text Message
			tableId : Search, //AD_Table_ID - Table - Database Table information
			priority : Option[Int], //Priority - Priority - Indicates if this request is of a high, medium or low priority.
			recordId : String, //Record_ID - Record ID - Direct internal record ID
			uuid : Option[String] //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
	) : WfProcess = WfProcess(
						created, //Created - Created - Date this record was created
						processing, //Processing - Process Now - 
						updated, //Updated - Updated - Date this record was updated
						processed, //Processed - Processed - The document has been processed
						isActive, //IsActive - Active - The record is active in the system
						workflowId, //AD_Workflow_ID - Workflow - Workflow or combination of tasks
						clientId, //AD_Client_ID - Client - Client/Tenant for this installation.
						createdBy, //CreatedBy - Created By - User who created this records
						orgId, //AD_Org_ID - Organization - Organizational entity within client
						wfState, //WFState - Workflow State - State of the execution of the workflow
						userId, //AD_User_ID - User/Contact - User within the system - Internal or Business Partner Contact
						updatedBy, //UpdatedBy - Updated By - User who updated this records
						wfResponsibleId, //AD_WF_Responsible_ID - Workflow Responsible - Responsible for Workflow Execution
						wfProcessId, //AD_WF_Process_ID - Workflow Process - Actual Workflow Process Instance
						messageId, //AD_Message_ID - Message - System Message
						textMsg, //TextMsg - Text Message - Text Message
						tableId, //AD_Table_ID - Table - Database Table information
						priority, //Priority - Priority - Indicates if this request is of a high, medium or low priority.
						recordId, //Record_ID - Record ID - Direct internal record ID
						uuid)  //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
}
