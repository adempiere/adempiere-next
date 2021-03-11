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
* WfEventAudit Algebraic Data Types domain model
* Workflow Event Audit
* Workflow Process Activity Event Audit Information
* 
*/

case class WfEventAudit(
				attributeName : Option[String], //AttributeName - Attribute Name - Name of the Attribute
				userId : Option[Search], //AD_User_ID - User/Contact - User within the system - Internal or Business Partner Contact
				wfResponsibleId : TableDirect, //AD_WF_Responsible_ID - Workflow Responsible - Responsible for Workflow Execution
				clientId : TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
				isActive : YesNo = true, //IsActive - Active - The record is active in the system
				updatedBy : Table, //UpdatedBy - Updated By - User who updated this records
				newValue : Option[String], //NewValue - New Value - New field value
				orgId : TableDirect, //AD_Org_ID - Organization - Organizational entity within client
				updated : DateTime, //Updated - Updated - Date this record was updated
				createdBy : Table, //CreatedBy - Created By - User who created this records
				wfState : ListType, //WFState - Workflow State - State of the execution of the workflow
				created : DateTime, //Created - Created - Date this record was created
				oldValue : Option[String], //OldValue - Old Value - The old file data
				wfEventAuditId : Id, //AD_WF_EventAudit_ID - Workflow Event Audit - Workflow Process Activity Event Audit Information
				eventType : ListType, //EventType - Event Type - Type of Event
				description : Option[String], //Description - Description - Optional short description of the record
				wfProcessId : TableDirect, //AD_WF_Process_ID - Workflow Process - Actual Workflow Process Instance
				tableId : TableDirect, //AD_Table_ID - Table - Database Table information
				textMsg : Option[String], //TextMsg - Text Message - Text Message
				wfNodeId : TableDirect, //AD_WF_Node_ID - Node - Workflow Node (activity), step or process
				recordId : String, //Record_ID - Record ID - Direct internal record ID
				elapsedTimeMS : Number, //ElapsedTimeMS - Elapsed Time ms - Elapsed Time in milli seconds
				uuid : Option[String], //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
) extends DomainModel
with ActiveEnabled
with Identifiable
with Traceable {
	override type ActiveEnabled = this.type
	override type Traceable = this.type
	override type Identifiable = this.type
	override val entityName: String = "AD_WF_EventAudit"
	override val identifier: String = "AD_WF_EventAudit_ID"
	override val Id: Id = wfEventAuditId
}


object WfEventAudit {
	def create(
			attributeName : Option[String], //AttributeName - Attribute Name - Name of the Attribute
			userId : Option[Search], //AD_User_ID - User/Contact - User within the system - Internal or Business Partner Contact
			wfResponsibleId : TableDirect, //AD_WF_Responsible_ID - Workflow Responsible - Responsible for Workflow Execution
			clientId : TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
			isActive : YesNo = true, //IsActive - Active - The record is active in the system
			updatedBy : Table, //UpdatedBy - Updated By - User who updated this records
			newValue : Option[String], //NewValue - New Value - New field value
			orgId : TableDirect, //AD_Org_ID - Organization - Organizational entity within client
			updated : DateTime, //Updated - Updated - Date this record was updated
			createdBy : Table, //CreatedBy - Created By - User who created this records
			wfState : ListType, //WFState - Workflow State - State of the execution of the workflow
			created : DateTime, //Created - Created - Date this record was created
			oldValue : Option[String], //OldValue - Old Value - The old file data
			wfEventAuditId : Id, //AD_WF_EventAudit_ID - Workflow Event Audit - Workflow Process Activity Event Audit Information
			eventType : ListType, //EventType - Event Type - Type of Event
			description : Option[String], //Description - Description - Optional short description of the record
			wfProcessId : TableDirect, //AD_WF_Process_ID - Workflow Process - Actual Workflow Process Instance
			tableId : TableDirect, //AD_Table_ID - Table - Database Table information
			textMsg : Option[String], //TextMsg - Text Message - Text Message
			wfNodeId : TableDirect, //AD_WF_Node_ID - Node - Workflow Node (activity), step or process
			recordId : String, //Record_ID - Record ID - Direct internal record ID
			elapsedTimeMS : Number, //ElapsedTimeMS - Elapsed Time ms - Elapsed Time in milli seconds
			uuid : Option[String] //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
	) : WfEventAudit = WfEventAudit(
						attributeName, //AttributeName - Attribute Name - Name of the Attribute
						userId, //AD_User_ID - User/Contact - User within the system - Internal or Business Partner Contact
						wfResponsibleId, //AD_WF_Responsible_ID - Workflow Responsible - Responsible for Workflow Execution
						clientId, //AD_Client_ID - Client - Client/Tenant for this installation.
						isActive, //IsActive - Active - The record is active in the system
						updatedBy, //UpdatedBy - Updated By - User who updated this records
						newValue, //NewValue - New Value - New field value
						orgId, //AD_Org_ID - Organization - Organizational entity within client
						updated, //Updated - Updated - Date this record was updated
						createdBy, //CreatedBy - Created By - User who created this records
						wfState, //WFState - Workflow State - State of the execution of the workflow
						created, //Created - Created - Date this record was created
						oldValue, //OldValue - Old Value - The old file data
						wfEventAuditId, //AD_WF_EventAudit_ID - Workflow Event Audit - Workflow Process Activity Event Audit Information
						eventType, //EventType - Event Type - Type of Event
						description, //Description - Description - Optional short description of the record
						wfProcessId, //AD_WF_Process_ID - Workflow Process - Actual Workflow Process Instance
						tableId, //AD_Table_ID - Table - Database Table information
						textMsg, //TextMsg - Text Message - Text Message
						wfNodeId, //AD_WF_Node_ID - Node - Workflow Node (activity), step or process
						recordId, //Record_ID - Record ID - Direct internal record ID
						elapsedTimeMS, //ElapsedTimeMS - Elapsed Time ms - Elapsed Time in milli seconds
						uuid)  //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
}
