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
* WorkflowProcessor Algebraic Data Types domain model
* Workflow Processor
* Workflow Processor Server
* 
*/

case class WorkflowProcessor(
				dateNextRun : Option[DateTime], //DateNextRun - Date next run - Date the process will run next
				description : Option[String], //Description - Description - Optional short description of the record
				keepLogDays : Int, //KeepLogDays - Days to keep Log - Number of days to keep the log entries
				frequencyType : ListType, //FrequencyType - Frequency Type - Frequency of event
				name : String, //Name - Name - Alphanumeric identifier of the entity
				processing : Option[String], //Processing - Process Now - 
				updatedBy : Table, //UpdatedBy - Updated By - User who updated this records
				clientId : TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
				orgId : TableDirect, //AD_Org_ID - Organization - Organizational entity within client
				workflowProcessorId : Id, //AD_WorkflowProcessor_ID - Workflow Processor - Workflow Processor Server
				isActive : YesNo = true, //IsActive - Active - The record is active in the system
				createdBy : Table, //CreatedBy - Created By - User who created this records
				frequency : Int, //Frequency - Frequency - Frequency of events
				updated : DateTime, //Updated - Updated - Date this record was updated
				created : DateTime, //Created - Created - Date this record was created
				supervisorId : Table, //Supervisor_ID - Supervisor - Supervisor for this user/organization - used for escalation and approval
				dateLastRun : Option[DateTime], //DateLastRun - Date last run - Date the process was last run.
				inactivityAlertDays : Option[Int] = Some(0), //InactivityAlertDays - Inactivity Alert Days - Send Alert when there is no activity after days (0= no alert)
				remindDays : Option[Int], //RemindDays - Reminder Days - Days between sending Reminder Emails for a due or inactive Document
				alertOverPriority : Option[Int], //AlertOverPriority - Alert over Priority - Send alert email when over priority
				uuid : Option[String], //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
) extends DomainModel
with ActiveEnabled
with Identifiable
with Traceable {
	override type ActiveEnabled = this.type
	override type Traceable = this.type
	override type Identifiable = this.type
	override val entityName: String = "AD_WorkflowProcessor"
	override val identifier: String = "AD_WorkflowProcessor_ID"
	override val Id: Id = workflowProcessorId
}


object WorkflowProcessor {
	def create(
			dateNextRun : Option[DateTime], //DateNextRun - Date next run - Date the process will run next
			description : Option[String], //Description - Description - Optional short description of the record
			keepLogDays : Int, //KeepLogDays - Days to keep Log - Number of days to keep the log entries
			frequencyType : ListType, //FrequencyType - Frequency Type - Frequency of event
			name : String, //Name - Name - Alphanumeric identifier of the entity
			processing : Option[String], //Processing - Process Now - 
			updatedBy : Table, //UpdatedBy - Updated By - User who updated this records
			clientId : TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
			orgId : TableDirect, //AD_Org_ID - Organization - Organizational entity within client
			workflowProcessorId : Id, //AD_WorkflowProcessor_ID - Workflow Processor - Workflow Processor Server
			isActive : YesNo = true, //IsActive - Active - The record is active in the system
			createdBy : Table, //CreatedBy - Created By - User who created this records
			frequency : Int, //Frequency - Frequency - Frequency of events
			updated : DateTime, //Updated - Updated - Date this record was updated
			created : DateTime, //Created - Created - Date this record was created
			supervisorId : Table, //Supervisor_ID - Supervisor - Supervisor for this user/organization - used for escalation and approval
			dateLastRun : Option[DateTime], //DateLastRun - Date last run - Date the process was last run.
			inactivityAlertDays : Option[Int] = Some(0), //InactivityAlertDays - Inactivity Alert Days - Send Alert when there is no activity after days (0= no alert)
			remindDays : Option[Int], //RemindDays - Reminder Days - Days between sending Reminder Emails for a due or inactive Document
			alertOverPriority : Option[Int], //AlertOverPriority - Alert over Priority - Send alert email when over priority
			uuid : Option[String] //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
	) : WorkflowProcessor = WorkflowProcessor(
						dateNextRun, //DateNextRun - Date next run - Date the process will run next
						description, //Description - Description - Optional short description of the record
						keepLogDays, //KeepLogDays - Days to keep Log - Number of days to keep the log entries
						frequencyType, //FrequencyType - Frequency Type - Frequency of event
						name, //Name - Name - Alphanumeric identifier of the entity
						processing, //Processing - Process Now - 
						updatedBy, //UpdatedBy - Updated By - User who updated this records
						clientId, //AD_Client_ID - Client - Client/Tenant for this installation.
						orgId, //AD_Org_ID - Organization - Organizational entity within client
						workflowProcessorId, //AD_WorkflowProcessor_ID - Workflow Processor - Workflow Processor Server
						isActive, //IsActive - Active - The record is active in the system
						createdBy, //CreatedBy - Created By - User who created this records
						frequency, //Frequency - Frequency - Frequency of events
						updated, //Updated - Updated - Date this record was updated
						created, //Created - Created - Date this record was created
						supervisorId, //Supervisor_ID - Supervisor - Supervisor for this user/organization - used for escalation and approval
						dateLastRun, //DateLastRun - Date last run - Date the process was last run.
						inactivityAlertDays, //InactivityAlertDays - Inactivity Alert Days - Send Alert when there is no activity after days (0= no alert)
						remindDays, //RemindDays - Reminder Days - Days between sending Reminder Emails for a due or inactive Document
						alertOverPriority, //AlertOverPriority - Alert over Priority - Send alert email when over priority
						uuid)  //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
}
